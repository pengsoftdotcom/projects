package com.pengsoft.support.domain.entity;

import com.pengsoft.support.commons.util.DateUtils;
import com.pengsoft.support.commons.util.StringUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import java.time.LocalDateTime;

/**
 * The Default implementer of {@link Beanable}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Data
@MappedSuperclass
public class Bean implements Beanable<String> {

    private static final long serialVersionUID = 314634114783440828L;

    @Id
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(name = "id-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Version
    private long version;


    /**
     * Before create.
     */
    @PrePersist
    public void preCreate() {
        final var now = DateUtils.currentDateTime();
        setCreatedAt(now);
        setUpdatedAt(now);
    }

    /**
     * Before update.
     */
    @PreUpdate
    public void preUpdate() {
        final var now = DateUtils.currentDateTime();
        setUpdatedAt(now);
    }

    @Override
    public String toString() {
        final var builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        if (this instanceof Codeable) {
            builder.append(StringUtils.GLOBAL_SEPARATOR).append(((Codeable) this).getCode());
        }
        builder.append(StringUtils.GLOBAL_SEPARATOR).append(getId());
        return builder.toString();
    }

}
