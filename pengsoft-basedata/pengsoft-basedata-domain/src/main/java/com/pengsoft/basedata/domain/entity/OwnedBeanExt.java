package com.pengsoft.basedata.domain.entity;

import com.pengsoft.security.domain.entity.OwnableBean;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

/**
 * Ownable extension bean
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@MappedSuperclass
public class OwnableExtBean extends OwnableBean {

    private static final long serialVersionUID = -5069906400895386605L;

    @Size(max = 255)
    @Column(updatable = false)
    private String controlledBy;

    @Size(max = 255)
    @Column(updatable = false)
    private String belongsTo;

}
