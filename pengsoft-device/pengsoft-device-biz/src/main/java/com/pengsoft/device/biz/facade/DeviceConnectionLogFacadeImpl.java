package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.DeviceConnectionLogService;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.DeviceConnectionLog;
import com.pengsoft.support.biz.facade.BeanFacadeImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link DeviceConnectionLogFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class DeviceConnectionLogFacadeImpl extends BeanFacadeImpl<DeviceConnectionLogService, DeviceConnectionLog, String> implements DeviceConnectionLogFacade {

    @Override
    public Page<DeviceConnectionLog> findPageByDevice(final Device device, final Pageable pageable) {
        return getService().findPageByDevice(device, pageable);
    }

}
