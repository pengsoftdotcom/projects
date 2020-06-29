package com.pengsoft.device.domain.entity;

import com.pengsoft.support.domain.entity.EntityImpl;
import com.pengsoft.support.domain.entity.Codeable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Product config
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_product_config", indexes = {
        @Index(name = "i_product_config_product_id_1", columnList = "product_id, code", unique = true),
        @Index(name = "i_product_config_product_id_2", columnList = "product_id, name", unique = true)
})
public class ProductConfig extends EntityImpl implements Codeable {

    private static final long serialVersionUID = -6100503660631968558L;

    @NotNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Product product;

    @NotBlank
    @Size(max = 255)
    private String code;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String type;

    @Size(max = 255)
    private String value;

    @Size(max = 255)
    private String remark;

}
