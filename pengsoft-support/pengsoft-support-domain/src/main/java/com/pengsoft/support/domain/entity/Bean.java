package com.pengsoft.support.domain.entity;

import com.pengsoft.support.commons.util.DateUtils;
import com.pengsoft.support.commons.util.StringUtils;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * The Default implementer of {@link Beanable}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@TypeDefs(@TypeDef(name = "array", typeClass = ListArrayType.class))
@MappedSuperclass
public class Bean implements Beanable<String> {

    private static final long serialVersionUID = 314634114783440828L;

    @Id
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(name = "id-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private long version;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public long getVersion() {
        return version;
    }

    @Override
    public void setVersion(final long version) {
        this.version = version;
    }

    /**
     * Before create.
     */
    @PrePersist
    public void prePresist() {
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
        if (this instanceof Codable) {
            builder.append(StringUtils.GLOBAL_SEPARATOR + ((Codable<>) this).getCode());
        }
        builder.append(StringUtils.GLOBAL_SEPARATOR + getId().toString());
        return builder.toString();
    }

}
