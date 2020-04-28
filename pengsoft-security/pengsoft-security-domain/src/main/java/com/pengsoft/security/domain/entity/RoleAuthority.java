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
 * Many-to-many relationship between {@link Role} and {@link Authority}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_role_authority", indexes = {
        @Index(name = "i_role_authority", columnList = "role_id, authority_id", unique = true)
})
public class RoleAuthority extends Bean {

    private static final long serialVersionUID = -4067024465738087267L;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Role role;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Authority authority;

    public RoleAuthority() {

    }

    public RoleAuthority(final Role role, final Authority authority) {
        this.role = role;
        this.authority = authority;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(final Authority authority) {
        this.authority = authority;
    }

}
