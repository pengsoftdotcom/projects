package com.pengsoft.security.domain.entity;

import com.pengsoft.support.domain.entity.Bean;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Many-to-many relationship between {@link User} and {@link Role}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_user_role", indexes = {
        @Index(name = "i_user_role", columnList = "user_id, role_id", unique = true)
})
public class UserRole extends Bean {

    private static final long serialVersionUID = 1105413409437052244L;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Role role;

    /**
     * Whether the major role.
     */
    private boolean major;

    public UserRole() {

    }

    public UserRole(final User user, final Role role) {
        this.user = user;
        this.role = role;
    }

    public UserRole(final User user, final Role role, final boolean major) {
        this.user = user;
        this.role = role;
        this.major = major;
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

    public boolean isMajor() {
        return major;
    }

    public void setMajor(final boolean major) {
        this.major = major;
    }

}
