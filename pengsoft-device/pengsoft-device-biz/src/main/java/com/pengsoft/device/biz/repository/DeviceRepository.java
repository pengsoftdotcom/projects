package com.pengsoft.device.biz.repository;

import java.util.Optional;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import com.pengsoft.basedata.biz.repository.OwnedExtRepository;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.Product;
import com.pengsoft.device.domain.entity.PurchaseBatchItem;
import com.pengsoft.device.domain.entity.QDevice;
import com.pengsoft.support.biz.repository.EntityRepository;
import com.querydsl.core.types.dsl.StringPath;

/**
 * The repository interface of {@link Device} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since  1.0.0
 */
@Repository
public interface DeviceRepository extends EntityRepository<QDevice, Device, String>, OwnedExtRepository {

    @Override
    default void customize(final QuerydslBindings bindings, final QDevice root) {
        EntityRepository.super.customize(bindings, root);
        bindings.bind(root.name).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link Device} with given code.
     *
     * @param code {@link Device}'s code
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Device> findOneByCode(@NotBlank String code);

    /**
     * Returns an {@link Optional} of a {@link Device} with given not activated and product and code.
     *
     * @param product {@link Device}'s product
     * @param code    {@link Device}'s code
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Device> findFirstByActivatedFalseAndProductAndCodeStartsWith(@NotNull Product product, @NotBlank String code);

    /**
     * Returns count of {@link Device} with given {@link PurchaseBatchItem} and activated.
     *
     * @param purchaseBatchItem {@link Device}'s purchaseBatchItem
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
    long countByPurchaseBatchItemAndActivatedTrue(@NotNull PurchaseBatchItem purchaseBatchItem);

}
