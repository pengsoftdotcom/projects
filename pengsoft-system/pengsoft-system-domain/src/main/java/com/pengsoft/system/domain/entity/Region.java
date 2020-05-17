package com.pengsoft.system.domain.entity;

import com.pengsoft.support.domain.entity.Codeable;
import com.pengsoft.support.domain.entity.TreeBean;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The Administrative division.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_region", indexes = {
        @Index(name = "i_region_code", columnList = "code", unique = true)
})
public class Region extends TreeBean<Region> implements Codeable {

    private static final long serialVersionUID = -3810895754139198521L;

    @NotBlank
    @Size(max = 255)
    private String code;

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String remark;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

}
