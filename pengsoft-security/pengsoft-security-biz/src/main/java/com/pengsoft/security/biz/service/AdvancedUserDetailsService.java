package com.pengsoft.security.biz.service;

import com.pengsoft.security.domain.DefaultUserDetails;
import com.pengsoft.security.domain.entity.Role;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * The advanced {@link UserDetailsService}
 *
 * @author dang.peng@pengsoft.com
 */
@Validated
public interface AdvancedUserDetailsService extends UserDetailsService {

    /**
     * Set the major role.
     *
     * @param role The major role.
     */
    void setMajorRole(@NotNull Role role);

    /**
     * Set the current role.
     *
     * @param role The current role.
     * @return {@link DefaultUserDetails}
     */
    DefaultUserDetails setCurrentRole(@NotNull Role role);

}
