package com.pengsoft.security.domain.entity;

import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.domain.entity.TreeBean;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * The owned tree entity.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@MappedSuperclass
public class OwnedTreeBean extends TreeBean<OwnedTreeBean> implements Owned {

    private static final long serialVersionUID = 8847928383056687590L;

    @Column(updatable = false)
    private String createdBy;

    private String updatedBy;

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(final String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public void preCreate() {
        super.preCreate();
        final var createdBy = SecurityUtils.getUserId();
        setCreatedBy(createdBy);
        setUpdatedBy(createdBy);
    }

    @Override
    public void preUpdate() {
        super.preUpdate();
        setUpdatedBy(SecurityUtils.getUserId());
    }

}
