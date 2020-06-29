package com.pengsoft.system.domain.entity;

import com.pengsoft.support.domain.entity.EntityImpl;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

/**
 * Message mapped superclass
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@MappedSuperclass
public class MessageMappedSuperclass extends EntityImpl {

    private static final long serialVersionUID = 4104623952728351613L;

    @Size(max = 255)
    private String subject;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String content;


    /**
     * {@link MessageType} string value, when multiple types are joined with ','
     */
    private String types;

}
