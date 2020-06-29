package com.pengsoft.system.domain.entity;

import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.domain.entity.EntityImpl;
import com.pengsoft.support.domain.entity.Codeable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Captcha
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_captcha", indexes = {
        @Index(name = "i_captcha_user_id", columnList = "user_id, expiredAt")
})
public class Captcha extends EntityImpl implements Codeable {

    private static final long serialVersionUID = 6091683647880038510L;

    @Size(max = 255)
    private String code;

    @NotNull
    private LocalDateTime expiredAt;

    @NotNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;

}
