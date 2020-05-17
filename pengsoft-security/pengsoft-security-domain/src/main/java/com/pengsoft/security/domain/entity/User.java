package com.pengsoft.security.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.pengsoft.security.commons.validation.Username;
import com.pengsoft.support.domain.entity.Bean;
import com.pengsoft.support.domain.entity.Enable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Basic user account information.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_user", indexes = {
        @Index(name = "i_user_username", columnList = "username", unique = true),
        @Index(name = "i_user_expired_at", columnList = "expiredAt")
})
public class User extends Bean implements Enable {

    private static final long serialVersionUID = 4025683878971039742L;

    @Username
    @Column(updatable = false)
    private String username;

    @JsonSerialize(using = NullSerializer.class)
    @Column(updatable = false)
    private String password;

    @Size(max = 255)
    private String locale = Locale.getDefault().toString();

    private LocalDateTime signedInAt;

    @Min(0)
    private long signInFailureCount;

    private LocalDateTime expiredAt;

    private boolean enabled = true;

    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<UserRole> userRoles = new ArrayList<>();

    public User() {

    }

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(final String locale) {
        this.locale = locale;
    }

    public LocalDateTime getSignedInAt() {
        return signedInAt;
    }

    public void setSignedInAt(final LocalDateTime signedInAt) {
        this.signedInAt = signedInAt;
    }

    public long getSignInFailureCount() {
        return signInFailureCount;
    }

    public void setSignInFailureCount(final long signInFailureCount) {
        this.signInFailureCount = signInFailureCount;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(final LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(final List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
