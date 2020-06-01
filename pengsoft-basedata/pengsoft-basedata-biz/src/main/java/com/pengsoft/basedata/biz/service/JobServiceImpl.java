package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.JobRepository;
import com.pengsoft.basedata.biz.repository.JobRoleRepository;
import com.pengsoft.basedata.domain.entity.Department;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.JobRole;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.support.biz.service.TreeBeanServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The implementer of {@link JobService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class JobServiceImpl extends TreeBeanServiceImpl<JobRepository, Job, String> implements JobService {

    @Inject
    private JobRoleRepository jobRoleRepository;

    @Override
    public Job save(final Job post) {
        findOneByDepartmentAndParentAndName(post.getDepartment(), post.getParent(), post.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, post)) {
                throw getExceptions().constraintViolated("name", "Exists", post.getName());
            }
        });
        return super.save(post);
    }

    @Override
    public void grantRoles(final Job job, final List<Role> roles) {
        final var source = job.getJobRoles();
        final var target = roles.stream().map(role -> new JobRole(job, role)).collect(Collectors.toList());
        final var deleted = source.stream()
                .filter(s -> target.stream().noneMatch(t -> EntityUtils.eq(s.getJob(), t.getJob()) && EntityUtils.eq(s.getRole(), t.getRole())))
                .collect(Collectors.toList());
        jobRoleRepository.deleteAll(deleted);
        source.removeAll(deleted);
        final var created = target.stream()
                .filter(t -> source.stream().noneMatch(s -> EntityUtils.eq(t.getJob(), s.getJob()) && EntityUtils.eq(t.getRole(), s.getRole())))
                .collect(Collectors.toList());
        jobRoleRepository.saveAll(created);
        source.addAll(created);
        super.save(job);
    }

    @Override
    public Optional<Job> findOneByDepartmentAndParentAndName(final Department department, final Job parent, final String name) {
        return getRepository().findOneByDepartmentAndParentAndName(department, parent, name);
    }

}
