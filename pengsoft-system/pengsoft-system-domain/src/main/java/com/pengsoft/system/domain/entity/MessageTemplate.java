package com.pengsoft.system.domain.entity;

import com.pengsoft.support.domain.entity.Codeable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * Message template
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_message_template", indexes = {
        @Index(name = "i_message_template_code", columnList = "code", unique = true),
        @Index(name = "i_message_template_subject", columnList = "subject")
})
public class MessageTemplate extends MessageMappedSuperclass implements Codeable {

    private static final long serialVersionUID = 2214719814087664062L;

    @NotBlank
    @Size(max = 255)
    private String code;

    @Size(max = 255)
    private String smsCode;

    @Size(max = 255)
    private String smsSignature;

    @Transient
    private transient Map<String, Object> params;

}
