package com.pengsoft.security.biz.api;

import com.pengsoft.security.biz.facade.UserFacade;
import com.pengsoft.security.biz.service.DefaultUserDetailsService;
import com.pengsoft.security.commons.annotation.Authorized;
import com.pengsoft.security.commons.annotation.UpdatingAuthentication;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.commons.exception.Exceptions;
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
@Authorized
@RestController
@RequestMapping("api/user-details")
public class UserDetailsApi {

    @Inject
    private Exceptions exceptions;

    @Inject
    private UserFacade userFacade;

    @Inject
    private DefaultUserDetailsService service;

    @GetMapping("current")
    public UserDetails current() {
        return SecurityUtils.getUserDetails();
    }

    @UpdatingAuthentication
    @PostMapping("set-primary-role")
    public UserDetails setPrimaryRole(@RequestParam("id") final Role role) {
        return service.setPrimaryRole(role);
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

    @PostMapping("reset-password")
    public void resetPassword(final String username, final String password) {
        var user = userFacade.findOneByMobile(username).orElseThrow(() -> exceptions.entityNotFound(username));
        userFacade.resetPassword(user.getId(), password);
    }

}
