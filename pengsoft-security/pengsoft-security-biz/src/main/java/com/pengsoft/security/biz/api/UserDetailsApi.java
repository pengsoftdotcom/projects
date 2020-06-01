package com.pengsoft.security.biz.api;

import com.pengsoft.security.biz.facade.UserFacade;
import com.pengsoft.security.biz.service.DefaultUserDetailsService;
import com.pengsoft.security.commons.annotation.Authenticated;
import com.pengsoft.security.commons.annotation.UpdatingAuthentication;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.util.SecurityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * The web api of {@link UserDetails}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Authenticated
@RestController
@RequestMapping("api/user-details")
public class UserDetailsApi {

    @Inject
    private UserFacade userFacade;

    @Inject
    private DefaultUserDetailsService service;

    @GetMapping("current")
    public UserDetails current() {
        return SecurityUtils.getUserDetails();
    }

    @UpdatingAuthentication
    @PostMapping("set-major-role")
    public UserDetails setMajorRole(@RequestParam("id") final Role role) {
        return service.setMajorRole(role);
    }

    @UpdatingAuthentication
    @PostMapping("set-current-role")
    public UserDetails setCurrentRole(@RequestParam("id") final Role role) {
        return service.setCurrentRole(role);
    }

    @PostMapping("change-password")
    public void changePassword(final String oldPassword, final String newPassword) {
        userFacade.changePassword(SecurityUtils.getUserId(), oldPassword, newPassword);
    }

}
