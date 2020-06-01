package com.pengsoft.basedata.biz.aspect;

import com.pengsoft.basedata.biz.facade.JobFacade;
import com.pengsoft.basedata.biz.facade.StaffFacade;
import com.pengsoft.basedata.domain.entity.OwnedExt;
import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.basedata.domain.entity.UserProfile;
import com.pengsoft.basedata.domain.util.SecurityUtilsExt;
import com.pengsoft.security.biz.aspect.DataAuthorityPredicateBuilder;
import com.pengsoft.security.biz.aspect.DefaultDataAuthorityPredicateBuilder;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.util.QueryDslUtils;
import com.pengsoft.support.domain.entity.Beanable;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.context.annotation.Primary;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Named
public class StaffDataAuthorityPredicateBuilder<T extends Beanable<ID>, ID extends Serializable> extends DefaultDataAuthorityPredicateBuilder<T, ID> implements DataAuthorityPredicateBuilder<T, ID> {

    @Inject
    private JobFacade jobFacade;

    @Inject
    private StaffFacade staffFacade;

    @Override
    public Predicate build(final Class<T> entityClass, final Predicate predicate) {
        if (OwnedExt.class.isAssignableFrom(entityClass)) {
            final var job = SecurityUtilsExt.getCurrentJob();
            if (job != null) {
                if (job.isOrganizationChief()) {
                    final var belongsToPath = (StringPath) QueryDslUtils.getPath(entityClass, "belongsTo");
                    return belongsToPath.eq(SecurityUtilsExt.getOrganizationId()).and(predicate);
                } else if (job.isDepartmentChief()) {
                    final var controlledByPath = (StringPath) QueryDslUtils.getPath(entityClass, "controlledBy");
                    return controlledByPath.eq(SecurityUtilsExt.getDepartmentId()).and(predicate);
                } else {
                    final var jobs = jobFacade.findAllByParentIdsStartsWith(job.getParentIds());
                    jobs.add(job);
                    final var staffs = staffFacade.findAllByJobIn(jobs);
                    final var createdByPath = (StringPath) QueryDslUtils.getPath(entityClass, "createdBy");
                    final var createdBy = staffs.stream().map(Staff::getUserProfile).map(UserProfile::getUser).map(User::getId).distinct().collect(Collectors.toList());
                    return createdByPath.in(createdBy).and(predicate);
                }
            }
        }
        return super.build(entityClass, predicate);
    }

}
