package com.pengsoft.device.domain.entity;

import com.pengsoft.basedata.domain.entity.OwnedBeanExt;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
@Table(name = "t_purchase_batch")
public class PurchaseBatch extends OwnedBeanExt {

    private static final long serialVersionUID = 248302104771741477L;

    @NotNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Product product;

    @Min(1)
    private int quantity = 1;

    @NotNull
    private LocalDate purchasedAt;

}
