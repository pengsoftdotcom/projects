package com.pengsoft.security.biz.api;

import com.pengsoft.security.biz.facade.RoleFacade;
import com.pengsoft.security.domain.entity.Authority;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.entity.RoleAuthority;
import com.pengsoft.support.biz.api.TreeBeanApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * The web api of {@link Role}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/role")
public class RoleApi extends TreeBeanApi<RoleFacade, Role, String> {

    @PostMapping("grant-authorities")
    public void grantAuthorities(@RequestParam("role.id") final Role role, @RequestParam(value = "authority.id", defaultValue = "") final List<Authority> authorities) {
        getFacade().grantAuthorities(role, authorities);
    }

    @GetMapping("find-all-role-authorities-by-role")
    public List<RoleAuthority> findAllRoleAuthoritiesByRole(@RequestParam("id") final Role role) {
        return Optional.ofNullable(role).map(Role::getRoleAuthorities).orElse(List.of());
    }

}
