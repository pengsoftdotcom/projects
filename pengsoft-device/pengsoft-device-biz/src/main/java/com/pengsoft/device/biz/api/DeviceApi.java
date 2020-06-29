package com.pengsoft.device.biz.api;

import com.pengsoft.device.biz.facade.DeviceFacade;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.support.biz.api.EntityApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Device}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/device")
public class DeviceApi extends EntityApi<DeviceFacade, Device, String> {

    @PostMapping("push-config")
    public void pushConfig(@RequestParam("id") final Device device) {
        getFacade().pushConfig(device);
    }

    @PostMapping("push-time")
    public void pushTime(@RequestParam("id") final Device device) {
        getFacade().pushTime(device);
    }

}
