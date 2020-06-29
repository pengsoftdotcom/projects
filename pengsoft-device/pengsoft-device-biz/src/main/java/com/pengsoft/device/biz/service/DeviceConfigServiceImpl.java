package com.pengsoft.device.biz.service;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.pengsoft.device.biz.repository.DeviceConfigRepository;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.DeviceConfig;
import com.pengsoft.support.biz.service.EntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;

/**
 * The implementer of {@link DeviceService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since  1.0.0
 */
@Primary
@Service
public class DeviceConfigServiceImpl extends EntityServiceImpl<DeviceConfigRepository, DeviceConfig, String> implements DeviceConfigService {

    @Override
    public DeviceConfig save(final DeviceConfig deviceConfig) {
        findOneByDeviceAndCode(deviceConfig.getDevice(), deviceConfig.getCode()).ifPresent(source -> {
            if (EntityUtils.ne(source, deviceConfig)) {
                throw getExceptions().constraintViolated("code", "Exists", deviceConfig.getCode());
            }
        });
        findOneByDeviceAndName(deviceConfig.getDevice(), deviceConfig.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, deviceConfig)) {
                throw getExceptions().constraintViolated("name", "Exists", deviceConfig.getName());
            }
        });
        return super.save(deviceConfig);
    }

    @Override
    public Optional<DeviceConfig> findOneByDeviceAndCode(final Device device, final String code) {
        return getRepository().findOneByDeviceAndCode(device, code);
    }

    @Override
    public Optional<DeviceConfig> findOneByDeviceAndName(final Device device, final String name) {
        return getRepository().findOneByDeviceAndName(device, name);
    }

}
