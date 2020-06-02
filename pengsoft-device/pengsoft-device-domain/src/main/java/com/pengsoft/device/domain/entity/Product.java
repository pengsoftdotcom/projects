package com.pengsoft.device.domain.entity;

import com.pengsoft.support.domain.entity.Bean;
import com.pengsoft.support.domain.entity.Codeable;
import com.pengsoft.system.domain.entity.DictionaryItem;
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
 * Product
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_product", indexes = {
        @Index(name = "i_product_code", columnList = "code", unique = true),
        @Index(name = "i_product_category_id", columnList = "category_id, name", unique = true)
})
public class Product extends Bean implements Codeable {

    private static final long serialVersionUID = -3314314410978706372L;

    @NotBlank
    @Size(max = 255)
    private String code;

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String contact;

    @NotNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private DictionaryItem category;

}
