package com.pengsoft.device.biz.service;

import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.Product;
import com.pengsoft.device.domain.entity.PurchaseBatchItem;
import com.pengsoft.support.biz.service.EntityService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * The service interface of {@link Device}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DeviceService extends EntityService<Device, String> {

    /**
     * Activate the device with given code
     */
    void activate(@NotBlank String code);

    /**
     * Bring the device online and record a log.
     *
     * @param device {@link Device}
     */
    void online(@NotNull Device device);

    /**
     * Bring the device offline and record a log.
     *
     * @param device {@link Device}
     */
    void offline(@NotNull Device device);

    /**
     * Push configurations to the device.
     *
     * @param device {@link Device}
     */
    void pushConfig(@NotNull Device device);

    /**
     * Push current time to the device.
     *
     * @param device {@link Device}
     */
    void pushTime(@NotNull Device device);

    /**
     * Returns an {@link Optional} of a {@link Device} with given code.
     *
     * @param code {@link Device}'s code
     */
    Optional<Device> findOneByCode(@NotBlank String code);

    /**
     * Returns an {@link Optional} of a {@link Device} with given not activated and product and code.
     *
     * @param product {@link Device}'s product
     * @param code    {@link Device}'s code
     */
    Optional<Device> findFirstByActivatedFalseAndProductAndCodeStartsWith(@NotNull Product product, @NotBlank String code);

    /**
     * Returns count of {@link Device} with given {@link PurchaseBatchItem} and activated.
     *
     * @param purchaseBatchItem {@link Device}'s purchaseBatchItem
     */
    long countByPurchaseBatchItemAndActivatedTrue(@NotNull PurchaseBatchItem purchaseBatchItem);

}
