package com.pengsoft.security.domain.entity;

import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.domain.entity.EntityImpl;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * {@link EntityImpl} implements {@link Owned}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@MappedSuperclass
public class OwnedEntity extends EntityImpl implements Owned {

    private static final long serialVersionUID = -1090095152411894992L;

    @Column(updatable = false)
    private String createdBy;

    private String updatedBy;

    @Override
    public void preCreate() {
        super.preCreate();
        final var userId = SecurityUtils.getUserId();
        setCreatedBy(userId);
        setUpdatedBy(userId);
    }

    @Override
    public void preUpdate() {
        super.preUpdate();
        final var userId = SecurityUtils.getUserId();
        setUpdatedBy(userId);
    }

}
