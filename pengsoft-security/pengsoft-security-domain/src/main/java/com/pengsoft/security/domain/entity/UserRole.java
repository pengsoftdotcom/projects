package com.pengsoft.security.domain.entity;

import com.pengsoft.support.domain.entity.Bean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
 * Many-to-many relationship between {@link User} and {@link Role}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor()
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_user_role", indexes = {
        @Index(name = "i_user_role", columnList = "user_id, role_id", unique = true)
})
public class UserRole extends Bean {

    private static final long serialVersionUID = 1105413409437052244L;

    @NonNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;

    @NonNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Role role;

    /**
     * Whether the major role.
     */
    private boolean major;

}
