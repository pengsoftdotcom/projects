package com.pengsoft.device.biz.api;

import com.pengsoft.device.biz.facade.DeviceFacade;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.support.biz.api.BeanApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Device}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/device")
public class DeviceApi extends BeanApi<DeviceFacade, Device, String> {

}
