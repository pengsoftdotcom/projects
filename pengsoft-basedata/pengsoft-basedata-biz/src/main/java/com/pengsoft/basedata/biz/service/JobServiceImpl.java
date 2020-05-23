package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.JobRepository;
import com.pengsoft.basedata.domain.entity.Department;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.support.biz.service.TreeBeanServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link JobService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class JobServiceImpl extends TreeBeanServiceImpl<JobRepository, Job, String> implements JobService {

    @Override
    public Job save(final Job post) {
        findOneByDepartmentAndParentAndName(post.getDepartment(), post.getParent(), post.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, post)) {
                throw exceptions.constraintViolated("name", "Exists", post.getName());
            }
        });
        return super.save(post);
    }

    @Override
    public Optional<Job> findOneByDepartmentAndParentAndName(final Department department, final Job parent, final String name) {
        return getRepository().findOneByDepartmentAndParentAndName(department, parent, name);
    }

}
