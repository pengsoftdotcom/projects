package com.pengsoft.system.domain.entity;

import com.pengsoft.security.domain.entity.User;
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
import java.time.LocalDateTime;

/**
 * Message
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_message", indexes = {
        @Index(name = "i_message_subject", columnList = "subject")
})
public class Message extends MessageMappedSuperclass {

    private static final long serialVersionUID = -1397382259106727612L;

    @NotNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private User sender;

    @NotNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private User recipient;

    private LocalDateTime sentAt;

    private LocalDateTime readAt;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private MessageTemplate template;

}
