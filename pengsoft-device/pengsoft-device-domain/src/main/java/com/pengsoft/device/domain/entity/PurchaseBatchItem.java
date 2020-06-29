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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Product purchased Batch
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_purchase_batch", indexes = {
        @Index(name = "purchase_batch_name", columnList = "name", unique = true)
})
public class PurchaseBatch extends OwnedBeanExt {

    private static final long serialVersionUID = -2631527128191884199L;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotNull
    private LocalDate purchasedAt;

}
