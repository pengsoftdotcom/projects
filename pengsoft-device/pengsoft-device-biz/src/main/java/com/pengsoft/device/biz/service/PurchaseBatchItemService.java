package com.pengsoft.device.biz.service;

import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.support.biz.service.BeanService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * The service interface of {@link Device}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DeviceService extends BeanService<Device, String> {

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
     * Returns an {@link Optional} of a {@link Device} with given code.
     *
     * @param code {@link Device}'s code
     */
    Optional<Device> findOneByCode(@NotBlank String code);

}
