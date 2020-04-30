package com.pengsoft.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.security.domain.json.GrantedAuthorityListSerializer;
import com.pengsoft.security.domain.json.RoleCollectionSerializer;
import com.pengsoft.security.domain.json.RoleSerializer;
import com.pengsoft.support.commons.util.DateUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * The default implementer of {@link UserDetails}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class DefaultUserDetails implements UserDetails {

    private static final long serialVersionUID = -6285842987366925156L;
    @JsonSerialize(using = RoleCollectionSerializer.class)
    private final List<Role> roles;

    @JsonIgnoreProperties({"id", "dateTimeCreated", "dateTimeUpdated", "version"})
    private User currentUser;

    @JsonSerialize(using = RoleSerializer.class)
    private Role currentRole;

    @JsonSerialize(using = GrantedAuthorityListSerializer.class)
    private Collection<? extends GrantedAuthority> authorities;

    public DefaultUserDetails(final User currentUser, final List<Role> roles) {
        this.currentUser = currentUser;
        this.roles = roles;
    }

    public DefaultUserDetails(final User currentUser, final List<Role> roles, final Role majorRole, final List<GrantedAuthority> authorities) {
        this.roles = roles;
        this.currentUser = currentUser;
        currentRole = majorRole;
        this.authorities = authorities;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    public Role getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(final Role currentRole) {
        this.currentRole = currentRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(final Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @JsonSerialize(using = NullSerializer.class)
    @Override
    public String getPassword() {
        if (currentUser != null) {
            return currentUser.getPassword();
        } else {
            return null;
        }
    }

    @Override
    public String getUsername() {
        if (currentUser != null) {
            return currentUser.getUsername();
        } else {
            return null;
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return currentUser == null || currentUser.getExpiredAt() == null
                || currentUser.getExpiredAt().isBefore(DateUtils.currentDateTime());
    }

    @Override
    public boolean isAccountNonLocked() {
        return currentUser == null || currentUser.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isAccountNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return currentUser == null || currentUser.isEnabled();
    }

}
