package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.DeviceService;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.support.biz.facade.BeanFacadeImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link DeviceFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class DeviceFacadeImpl extends BeanFacadeImpl<DeviceService, Device, String> implements DeviceFacade {

    @Override
    public void activate(final String code) {
        getService().activate(code);
    }

    @Override
    public void online(final Device device) {
        getService().online(device);
    }

    @Override
    public void offline(final Device device) {
        getService().offline(device);
    }

    @Override
    public Optional<Device> findOneByCode(final String code) {
        return getService().findOneByCode(code);
    }

}
