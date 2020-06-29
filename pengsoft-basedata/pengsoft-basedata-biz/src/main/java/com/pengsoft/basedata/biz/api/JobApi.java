package com.pengsoft.basedata.biz.api;

import com.pengsoft.basedata.biz.facade.JobFacade;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.JobRole;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.support.biz.api.TreeEntityApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * The web api of {@link Job}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/job")
public class JobApi extends TreeEntityApi<JobFacade, Job, String> {

    @PostMapping("grant-roles")
    public void grantRoles(@RequestParam("job.id") final Job job, @RequestParam(value = "role.id", defaultValue = "") final List<Role> roles) {
        getFacade().grantRoles(job, roles);
    }

    @GetMapping("find-all-job-roles-by-job")
    public List<JobRole> findAllJobRolesByJob(@RequestParam("id") final Job job) {
        return Optional.ofNullable(job).map(Job::getJobRoles).orElse(List.of());
    }

}
