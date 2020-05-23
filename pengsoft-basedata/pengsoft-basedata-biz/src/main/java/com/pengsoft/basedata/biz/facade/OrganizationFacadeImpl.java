package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.DepartmentService;
import com.pengsoft.basedata.biz.service.JobService;
import com.pengsoft.basedata.biz.service.OrganizationService;
import com.pengsoft.basedata.biz.service.PostService;
import com.pengsoft.basedata.domain.entity.Department;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.basedata.domain.entity.Post;
import com.pengsoft.support.biz.facade.TreeBeanFacadeImpl;
import com.pengsoft.support.commons.util.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The implementer of {@link OrganizationFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class OrganizationFacadeImpl extends TreeBeanFacadeImpl<OrganizationService, Organization, String> implements OrganizationFacade {

    @Inject
    private MessageSource messageSource;

    @Inject
    private PostService postService;

    @Inject
    private DepartmentService departmentService;

    @Inject
    private JobService jobService;

    @Override
    public Organization save(final Organization organization) {
        final var creating = StringUtils.isBlank(organization.getId());
        super.save(organization);
        if (creating) {
            final var post = new Post();
            post.setOrganization(organization);
            post.setName(messageSource.getMessage("default.post.name", null, LocaleContextHolder.getLocale()));
            postService.save(post);

            final var department = new Department();
            department.setOrganization(organization);
            department.setName(messageSource.getMessage("default.department.name", null, LocaleContextHolder.getLocale()));
            departmentService.save(department);

            final var job = new Job();
            job.setPost(post);
            job.setDepartment(department);
            job.setName(messageSource.getMessage("default.post.name", null, LocaleContextHolder.getLocale()));
            jobService.save(job);
        }
        return organization;
    }

    @Override
    public Optional<Organization> findOneByCode(@NotBlank final String code) {
        return getService().findOneByCode(code);
    }

    @Override
    public Optional<Organization> findOneByName(@NotBlank final String name) {
        return getService().findOneByName(name);
    }

}
