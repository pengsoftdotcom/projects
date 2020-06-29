package com.pengsoft.security.biz.api;

import com.pengsoft.security.biz.facade.UserFacade;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.security.domain.entity.UserRole;
import com.pengsoft.support.biz.api.EntityApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * The web api of {@link User}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/user")
public class UserApi extends EntityApi<UserFacade, User, String> {

    @PostMapping("reset-password")
    public void resetPassword(final String id, final String password) {
        getFacade().resetPassword(id, password);
    }

    @PostMapping("grant-roles")
    public void grantRoles(@RequestParam("user.id") final User user, @RequestParam(value = "role.id", defaultValue = "") final List<Role> roles) {
        getFacade().grantRoles(user, roles);
    }

    @PostMapping("set-primary-role")
    public void setPrimaryRole(@RequestParam("user.id") final User user, @RequestParam("role.id") final Role role) {
        getFacade().setPrimaryRole(user, role);
    }

    @GetMapping("find-all-user-roles-by-user")
    public List<UserRole> findAllUserRolesByUser(@RequestParam("id") final User user) {
        return Optional.ofNullable(user).map(User::getUserRoles).orElse(List.of());
    }

}
