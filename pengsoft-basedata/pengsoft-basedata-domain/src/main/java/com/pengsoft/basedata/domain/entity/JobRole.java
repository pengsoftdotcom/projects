package com.pengsoft.basedata.domain.entity;

import com.pengsoft.security.domain.entity.Role;
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
 * Many-to-many relationship between {@link Job} and {@link Role}.
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
@Table(name = "t_job_role", indexes = {
        @Index(name = "i_job_role", columnList = "job_id, role_id", unique = true)
})
public class JobRole extends Bean {

    private static final long serialVersionUID = -2605345847395420443L;

    @NonNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Job job;

    @NonNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Role role;

}
