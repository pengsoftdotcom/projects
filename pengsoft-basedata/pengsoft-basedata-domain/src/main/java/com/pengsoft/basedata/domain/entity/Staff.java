package com.pengsoft.basedata.domain.entity;

import com.pengsoft.support.domain.entity.Bean;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Staff
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_staff", indexes = {
        @Index(name = "i_staff_user_profile_id", columnList = "user_profile_id, job_id", unique = true)
})
public class Staff extends Bean {

    private static final long serialVersionUID = -5537370953204778193L;

    @Valid
    @NotNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private UserProfile userProfile = new UserProfile();

    @NotNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Job job;

    private boolean major = true;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Department department;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Organization organization;

}
