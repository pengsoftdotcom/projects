package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.PersonUserDetails;
import com.pengsoft.basedata.domain.StaffUserDetails;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.Organization;
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
     * Set the primary job.
     *
     * @param job The primary job.
     * @return {@link StaffUserDetails}
     */
    DefaultUserDetails setPrimaryJob(@NotNull Job job);

    /**
     * Set the current job.
     *
     * @param job The current job.
     * @return {@link StaffUserDetails}
     */
    DefaultUserDetails setCurrentJob(@NotNull Job job);

    /**
     * set the current organization
     *
     * @param organization The current organization.
     * @Return {@link PersonUserDetails}
     */
    DefaultUserDetails setOrganization(@NotNull Organization organization);


}
