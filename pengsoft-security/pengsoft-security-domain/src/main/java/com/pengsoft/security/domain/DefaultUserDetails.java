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

    @JsonIgnoreProperties({ "id", "dateTimeCreated", "dateTimeUpdated", "version" })
    private User user;

    @JsonSerialize(using = RoleSerializer.class)
    private Role role;

    @JsonSerialize(using = GrantedAuthorityListSerializer.class)
    private Collection<? extends GrantedAuthority> authorities;

    public DefaultUserDetails(final User user, final List<Role> roles) {
        this.user = user;
        this.roles = roles;
    }

    public DefaultUserDetails(final User user, final List<Role> roles, final Role majorRole, final List<GrantedAuthority> authorities) {
        this.roles = roles;
        this.user = user;
        this.role = majorRole;
        this.authorities = authorities;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
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
        if (user != null) {
            return user.getPassword();
        } else {
            return null;
        }
    }

    @Override
    public String getUsername() {
        if (user != null) {
            return user.getUsername();
        } else {
            return null;
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return user == null || user.getExpiredAt() == null
                || user.getExpiredAt().isBefore(DateUtils.currentDateTime());
    }

    @Override
    public boolean isAccountNonLocked() {
        return user == null || user.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isAccountNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user == null || user.isEnabled();
    }

}
