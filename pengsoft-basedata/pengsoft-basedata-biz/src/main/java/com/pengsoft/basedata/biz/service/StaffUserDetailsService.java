package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.StaffUserDetails;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.security.biz.service.DefaultUserDetailsService;
import com.pengsoft.security.domain.DefaultUserDetails;

import javax.validation.constraints.NotNull;

/**
 * User details service for {@link }
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface StaffUserDetailsService extends DefaultUserDetailsService {

    /**
     * Set the major job.
     *
     * @param job The major job.
     * @return {@link DefaultUserDetails}
     */
    StaffUserDetails setMajorJob(@NotNull Job job);

    /**
     * Set the current job.
     *
     * @param job The current job.
     * @return {@link DefaultUserDetails}
     */
    StaffUserDetails setCurrentJob(@NotNull Job job);


}
