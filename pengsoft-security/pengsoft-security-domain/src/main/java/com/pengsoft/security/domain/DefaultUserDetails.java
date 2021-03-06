package com.pengsoft.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.security.domain.json.GrantedAuthorityCollectionJsonSerializer;
import com.pengsoft.security.domain.json.RoleCollectionJsonSerializer;
import com.pengsoft.security.domain.json.RoleJsonSerializer;
import com.pengsoft.support.commons.util.DateUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * The default implementer of {@link UserDetails}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class DefaultUserDetails implements UserDetails {

    private static final long serialVersionUID = 8035952045100093641L;

    @Getter
    @Setter
    @JsonSerialize(using = RoleCollectionJsonSerializer.class)
    private Collection<Role> roles;

    @Getter
    @Setter
    @JsonIgnoreProperties({ "id", "dateTimeCreated", "dateTimeUpdated", "version" })
    private User user;

    @Getter
    @Setter
    @JsonSerialize(using = RoleJsonSerializer.class)
    private Role currentRole;

    @Getter
    @Setter
    @JsonSerialize(using = RoleJsonSerializer.class)
    private Role primaryRole;

    @Getter
    @Setter
    @JsonSerialize(using = GrantedAuthorityCollectionJsonSerializer.class)
    private Collection<? extends GrantedAuthority> authorities;

    public DefaultUserDetails(final User user, final Collection<Role> roles) {
        this.user = user;
        this.roles = roles;
    }

    public DefaultUserDetails(final User user, final Collection<Role> roles, final Role primaryRole, final Collection<? extends GrantedAuthority> authorities) {
        this.roles = roles;
        this.user = user;
        this.primaryRole = primaryRole;
        currentRole = primaryRole;
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
