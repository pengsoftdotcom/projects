package com.pengsoft.device.domain.entity;

import com.pengsoft.basedata.domain.entity.OwnedBeanExt;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Device group
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_group", indexes = {
        @Index(name = "i_group_name", columnList = "name", unique = true)
})
public class Group extends OwnedBeanExt {

    private static final long serialVersionUID = 8629481529378003073L;

    @NotBlank
    @Size(max = 255)
    private String name;

}
