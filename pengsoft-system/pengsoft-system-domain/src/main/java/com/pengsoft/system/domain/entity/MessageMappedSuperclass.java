package com.pengsoft.system.domain.entity;

import com.pengsoft.support.domain.entity.Bean;
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
@MappedSuperclass
public class MessageMappedSuperclass extends Bean {

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(final String types) {
        this.types = types;
    }

}
