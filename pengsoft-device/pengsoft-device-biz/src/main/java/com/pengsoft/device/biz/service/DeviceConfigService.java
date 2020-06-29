package com.pengsoft.device.biz.service;

import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.DeviceConfig;
import com.pengsoft.support.biz.service.EntityService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * The service interface of {@link DeviceConfig}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DeviceConfigService extends EntityService<DeviceConfig, String> {

    /**
     * Returns an {@link Optional} of a {@link DeviceConfig} with given device and code.
     *
     * @param device {@link DeviceConfig}'s device
     * @param code   {@link DeviceConfig}'s code
     */
    Optional<DeviceConfig> findOneByDeviceAndCode(@NotNull Device device, @NotBlank String code);

    /**
     * Returns an {@link Optional} of a {@link DeviceConfig} with given device and name.
     *
     * @param device {@link DeviceConfig}'s device
     * @param name   {@link DeviceConfig}'s name
     */
    Optional<DeviceConfig> findOneByDeviceAndName(@NotNull Device device, @NotBlank String name);

}
