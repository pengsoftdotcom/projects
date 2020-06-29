package com.pengsoft.device.biz.api;

import com.pengsoft.device.biz.facade.DeviceConnectionLogFacade;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.DeviceConnectionLog;
import com.pengsoft.support.biz.api.BeanApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link DeviceConnectionLog}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/device-log")
public class DeviceConnectionLogApi extends BeanApi<DeviceConnectionLogFacade, DeviceConnectionLog, String> {

    public Page<DeviceConnectionLog> findPageByDevice(@RequestParam("device.id") final Device device, final Pageable pageable) {
        return getFacade().findPageByDevice(device, pageable);
    }

}
