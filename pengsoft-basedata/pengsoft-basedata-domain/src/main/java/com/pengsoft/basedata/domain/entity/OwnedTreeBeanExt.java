package com.pengsoft.basedata.domain.entity;

import com.pengsoft.security.domain.entity.OwnableTreeBean;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

/**
 * Ownable extension tree bean
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@MappedSuperclass
public class OwnableExtTreeBean extends OwnableTreeBean {

    private static final long serialVersionUID = 4530346280009240007L;

    @Size(max = 255)
    @Column(updatable = false)
    private String controlledBy;

    @Size(max = 255)
    @Column(updatable = false)
    private String belongsTo;

}
