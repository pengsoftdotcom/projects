package com.pengsoft.device.biz.api;

import com.pengsoft.device.biz.facade.DeviceConfigFacade;
import com.pengsoft.device.domain.entity.DeviceConfig;
import com.pengsoft.support.biz.api.EntityApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link DeviceConfig}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/device-config")
public class DeviceConfigApi extends EntityApi<DeviceConfigFacade, DeviceConfig, String> {

}
