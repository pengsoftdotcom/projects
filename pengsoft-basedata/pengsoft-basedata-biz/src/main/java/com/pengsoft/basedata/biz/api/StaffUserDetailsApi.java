package com.pengsoft.basedata.biz.api;

import com.pengsoft.basedata.biz.service.StaffUserDetailsService;
import com.pengsoft.basedata.domain.StaffUserDetails;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.security.commons.annotation.Authenticated;
import com.pengsoft.security.commons.annotation.UpdatingAuthentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * The web api of {@link StaffUserDetails}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Authenticated
@RestController
@RequestMapping("api/user-details")
public class StaffUserDetailsApi {

    @Inject
    private StaffUserDetailsService service;

    @UpdatingAuthentication
    @PostMapping("set-major-job")
    public UserDetails setMajorJob(@RequestParam("id") final Job job) {
        return service.setMajorJob(job);
    }

    @UpdatingAuthentication
    @PostMapping("set-current-job")
    public UserDetails setCurrentJob(@RequestParam("id") final Job job) {
        return service.setCurrentJob(job);
    }

}
