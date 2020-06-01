package com.pengsoft.security.domain.entity;

import com.pengsoft.support.domain.entity.Bean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
