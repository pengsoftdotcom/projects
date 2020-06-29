package com.pengsoft.device.biz.api;

import com.pengsoft.device.biz.facade.DeviceGroupFacade;
import com.pengsoft.device.domain.entity.DeviceGroup;
import com.pengsoft.support.biz.api.TreeEntityApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link DeviceGroup}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/device-group")
public class DeviceGroupApi extends TreeEntityApi<DeviceGroupFacade, DeviceGroup, String> {

}
