package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.DeviceConfigService;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.DeviceConfig;
import com.pengsoft.support.biz.facade.EntityFacadeImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link DeviceConfigFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class DeviceConfigFacadeImpl extends EntityFacadeImpl<DeviceConfigService, DeviceConfig, String> implements DeviceConfigFacade {

    @Override
    public Optional<DeviceConfig> findOneByDeviceAndCode(final Device device, final String code) {
        return getService().findOneByDeviceAndCode(device, code);
    }

    @Override
    public Optional<DeviceConfig> findOneByDeviceAndName(final Device device, final String name) {
        return getService().findOneByDeviceAndName(device, name);
    }

}
