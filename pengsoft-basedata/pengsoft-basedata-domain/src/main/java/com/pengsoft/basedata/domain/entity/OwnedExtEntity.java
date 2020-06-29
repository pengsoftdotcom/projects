package com.pengsoft.basedata.domain.entity;

import com.pengsoft.basedata.domain.util.SecurityUtilsExt;
import com.pengsoft.security.domain.entity.OwnedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * {@link OwnedEntity} implements {@link OwnedExt}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@MappedSuperclass
public class OwnedExtEntity extends OwnedEntity implements OwnedExt {

    private static final long serialVersionUID = 7604551680091764860L;

    @Column(updatable = false)
    private String controlledBy;

    @Column(updatable = false)
    private String belongsTo;

    @Override
    public void preCreate() {
        super.preCreate();
        setControlledBy(SecurityUtilsExt.getDepartmentId());
        setBelongsTo(SecurityUtilsExt.getOrganizationId());
    }

}
