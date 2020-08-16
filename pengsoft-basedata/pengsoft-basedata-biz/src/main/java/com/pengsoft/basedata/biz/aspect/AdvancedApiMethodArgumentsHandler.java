package com.pengsoft.basedata.biz.aspect;

import com.pengsoft.basedata.biz.facade.JobFacade;
import com.pengsoft.basedata.biz.facade.OrganizationFacade;
import com.pengsoft.basedata.biz.facade.StaffFacade;
import com.pengsoft.basedata.biz.repository.OwnedExtRepository;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.basedata.domain.entity.OwnedExt;
import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.basedata.domain.util.SecurityUtilsExt;
import com.pengsoft.security.biz.aspect.ApiMethodArgumentsHandler;
import com.pengsoft.security.biz.aspect.DefaultApiMethodArgumentsHandler;
import com.pengsoft.security.domain.entity.Owned;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.biz.util.QueryDslUtils;
import com.pengsoft.support.commons.exception.MissingConfigurationException;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.domain.entity.Entity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * The implementer of {@link ApiMethodArgumentsHandler}, add implementation for {@link OwnedExt}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Named
public class AdvancedApiMethodArgumentsHandler<T extends Entity<ID>, ID extends Serializable> extends DefaultApiMethodArgumentsHandler<T, ID> {

    public static final String ORGANIZATION_ADMIN = "organization_admin";

    @Inject
    private OrganizationFacade organizationFacade;

    @Inject
    private JobFacade jobFacade;

    @Inject
    private StaffFacade staffFacade;

    public AdvancedApiMethodArgumentsHandler(final ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public Predicate replace(final Class<T> entityClass, final Predicate predicate) {
        if (SecurityUtilsExt.hasAnyRole(Role.ADMIN, getModuleAdminRoleCode(entityClass), getEntityAdminRoleCode(entityClass))) {
            return predicate;
        } else {
            if (OwnedExt.class.isAssignableFrom(entityClass)) {
                final var result = new BooleanBuilder();
                Organization organization = null;
                if (isOrganizationAdmin()) {
                    organization = SecurityUtilsExt.getOrganization();
                } else {
                    organization = SecurityUtilsExt.getOrganization();
                    final var job = SecurityUtilsExt.getCurrentJob();
                    if (job != null) {
                        result.or(getCreatedByPredicate(entityClass, job));
                    }
                }
                result.or(getBelongsToPredicate(entityClass, organization));
                if (QueryDslUtils.isNotBlank(result)) {
                    return QueryDslUtils.merge(predicate, result);
                }
            }
            return super.replace(entityClass, predicate);
        }
    }

    private BooleanExpression getBelongsToPredicate(final Class<T> entityClass, final Organization organization) {
        final var organizations = organizationFacade
                .findAllByParentIdsStartsWith(organization.getParentIds() + StringUtils.GLOBAL_SEPARATOR + organization.getId());
        organizations.add(organization);
        final var belongsToPath = (StringPath) QueryDslUtils.getPath(entityClass, "belongsTo");
        return belongsToPath.in(organizations.stream().map(Organization::getId).collect(Collectors.toList()));
    }

    private BooleanExpression getCreatedByPredicate(final Class<T> entityClass, final Job job) {
        final var jobs = jobFacade.findAllByParentIdsStartsWith(job.getParentIds() + StringUtils.GLOBAL_SEPARATOR + job.getId());
        jobs.add(job);
        final var staffs = staffFacade.findAllByJobIn(jobs);
        final var createdByPath = (StringPath) QueryDslUtils.getPath(entityClass, "createdBy");
        final var createdBy = staffs.stream().map(Staff::getPerson).map(Person::getUser).map(User::getId).distinct()
                .collect(Collectors.toList());
        return createdByPath.in(createdBy);
    }

    private boolean isOrganizationAdmin() {
        final var role = SecurityUtils.getCurrentRole();
        return role != null && StringUtils.equals(ORGANIZATION_ADMIN, role.getCode());
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean check(final Owned entity) {
        final var id = ((T) entity).getId();
        if (id == null) {
            return true;
        } else {
            final var entityClass = (Class<T>) entity.getClass();
            if (SecurityUtilsExt.hasAnyRole(Role.ADMIN, getModuleAdminRoleCode(entityClass), getEntityAdminRoleCode(entityClass))) {
                return true;
            } else {
                if (OwnedExt.class.isAssignableFrom(entity.getClass()) && SecurityUtilsExt.getCurrentJob() != null) {
                    final var job = SecurityUtilsExt.getCurrentJob();
                    if (job.isOrganizationChief() && StringUtils.equals(SecurityUtilsExt.getOrganizationId(), ((OwnedExt) entity).getBelongsTo())) {
                        return true;
                    }
                    if (job.isDepartmentChief() && StringUtils.equals(SecurityUtilsExt.getOrganizationId(), ((OwnedExt) entity).getControlledBy())) {
                        return true;
                    }
                }
                return super.check(entity);
            }
        }

    }

    @Override
    public boolean check(final Class<T> entityClass, final Collection<String> ids) {
        if (SecurityUtilsExt.hasAnyRole(Role.ADMIN, getModuleAdminRoleCode(entityClass), getEntityAdminRoleCode(entityClass))) {
            return true;
        } else {
            if (OwnedExt.class.isAssignableFrom(entityClass) && SecurityUtilsExt.getCurrentJob() != null) {
                final var repository = (OwnedExtRepository) getRepositories().getRepositoryFor(entityClass)
                        .orElseThrow(() -> new MissingConfigurationException("no repository for class: " + entityClass.getName()));
                final var job = SecurityUtilsExt.getCurrentJob();
                boolean matched = false;
                if (job.isOrganizationChief()) {
                    matched = repository.countByIdInAndBelongsTo(ids, SecurityUtilsExt.getOrganizationId()) == ids.size();
                }

                if (job.isDepartmentChief()) {
                    matched = repository.countByIdInAndControlledBy(ids, SecurityUtilsExt.getDepartmentId()) == ids.size();
                }

                if (matched) {
                    return true;
                }
            }
            return super.check(entityClass, ids);
        }
    }

}
