package com.pengsoft.basedata.domain.entity;

import com.pengsoft.basedata.domain.util.SecurityUtilsExt;
import com.pengsoft.security.domain.entity.OwnedTreeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * {@link OwnedTreeEntity} implements {@link OwnedExt}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@MappedSuperclass
public class OwnedExtTreeEntity<T extends OwnedTreeEntity<T>> extends OwnedTreeEntity<T> implements OwnedExt {


    private static final long serialVersionUID = -326799436860550542L;

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
