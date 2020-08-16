package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.JobService;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.support.biz.facade.TreeEntityFacadeImpl;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The implementer of {@link JobFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class JobFacadeImpl extends TreeEntityFacadeImpl<JobService, Job, String> implements JobFacade {

    @Override
    public void grantRoles(@NotNull final Job job, final List<Role> roles) {
        getService().grantRoles(job, roles);
    }

}
