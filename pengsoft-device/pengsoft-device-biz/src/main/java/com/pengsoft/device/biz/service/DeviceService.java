package com.pengsoft.device.biz.service;

import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.support.biz.service.BeanService;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The service interface of {@link Device}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DeviceService extends BeanService<Device, String> {

    /**
     * Returns an {@link Optional} of a {@link Device} with given code.
     *
     * @param code {@link Device}'s code
     */
    Optional<Device> findOneByCode(@NotBlank String code);

}
