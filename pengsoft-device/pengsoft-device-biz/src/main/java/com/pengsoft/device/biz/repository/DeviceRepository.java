package com.pengsoft.device.biz.repository;

import com.pengsoft.basedata.biz.repository.OwnedExtRepository;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.PurchaseBatchItem;
import com.pengsoft.device.domain.entity.QDevice;
import com.pengsoft.support.biz.repository.EntityRepository;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The repository interface of {@link Device} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
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
     * Returns an {@link Optional} of a {@link Device} with given not activated, product id and code.
     *
     * @param productId The id of {@link Device}'s product
     * @param code      {@link Device}'s code
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Device> findFirstByActivatedFalseAndProductIdAndCodeStartsWith(@NotBlank String productId, @NotBlank String code);

    /**
     * Returns count of {@link Device} with given {@link PurchaseBatchItem} id and activated.
     *
     * @param purchaseBatchItemId The id of {@link Device}'s purchaseBatchItem
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
    long countByPurchaseBatchItemIdAndActivatedTrue(@NotBlank String purchaseBatchItemId);

}
