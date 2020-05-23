package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.JobService;
import com.pengsoft.basedata.domain.entity.Department;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.support.biz.facade.TreeBeanFacadeImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link JobFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class JobFacadeImpl extends TreeBeanFacadeImpl<JobService, Job, String> implements JobFacade {

    @Override
    public Optional<Job> findOneByDepartmentAndParentAndName(final Department department, final Job parent, final String name) {
        return getService().findOneByDepartmentAndParentAndName(department, parent, name);
    }

}
