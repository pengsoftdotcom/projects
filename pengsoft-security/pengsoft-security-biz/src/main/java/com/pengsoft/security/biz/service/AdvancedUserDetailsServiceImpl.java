package com.pengsoft.security.biz.service;

import com.pengsoft.security.domain.DefaultGrantedAuthority;
import com.pengsoft.security.domain.DefaultUserDetails;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.entity.RoleAuthority;
import com.pengsoft.security.domain.entity.UserRole;
import com.pengsoft.security.domain.util.SecurityUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The implementer of {@link AdvancedUserDetailsService}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class AdvancedUserDetailsServiceImpl implements AdvancedUserDetailsService {

    @Inject
    private MessageSource messageSource;

    @Lazy
    @Inject
    private UserService userService;

    private static List<GrantedAuthority> getAllAuthorities(final Role role) {
        final var roles = new ArrayList<Role>();
        roles.add(role);
        final var deque = new ArrayDeque<Role>();
        role.getChildren().forEach(root -> {
            deque.push(root);
            while (!deque.isEmpty()) {
                final var parent = deque.pop();
                parent.setParent(null);
                roles.add(parent);
                parent.getChildren().forEach(deque::push);
            }
        });
        return roles.stream().map(Role::getRoleAuthorities)
                .flatMap(List::stream)
                .map(RoleAuthority::getAuthority)
                .map(DefaultGrantedAuthority::new)
                .distinct()
                .collect(Collectors.toList());
    }

    protected void saveAccessToken(final UserDetails userDetails) {
        //TODO Implement it.
    }

    @Override
    public void setMajorRole(final Role role) {
        userService.setMajorRole(SecurityUtils.getUser(), role);
    }

    @Override
    public DefaultUserDetails setCurrentRole(final Role role) {
        final var userDetails = SecurityUtils.getUserDetails();
        if (userDetails != null) {
            userDetails.setRole(role);
            saveAccessToken(userDetails);
        }
        return userDetails;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final var user = userService.findOneByUsername(username).orElseThrow(() -> new UsernameNotFoundException("'" + username + "' not found"));
        final var roles = user.getUserRoles().stream().map(UserRole::getRole).collect(Collectors.toList());
        final var optional = user.getUserRoles().stream().filter(UserRole::isMajor).map(UserRole::getRole).findFirst();
        if (optional.isPresent()) {
            final var majorRole = optional.get();
            return new DefaultUserDetails(user, roles, majorRole, getAllAuthorities(majorRole));
        } else {
            return new DefaultUserDetails(user, roles);
        }
    }

}
