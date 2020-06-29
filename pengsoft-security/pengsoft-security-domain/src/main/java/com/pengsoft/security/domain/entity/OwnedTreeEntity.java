package com.pengsoft.security.domain.entity;

import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.domain.entity.TreeEntityImpl;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * {@link TreeEntityImpl} implements {@link Owned}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@MappedSuperclass
public class OwnedTreeEntity<T extends OwnedTreeEntity<T>> extends TreeEntityImpl<T> implements Owned {

    private static final long serialVersionUID = -3107782926731669275L;

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
