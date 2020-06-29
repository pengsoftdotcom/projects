package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.DeviceGroupService;
import com.pengsoft.device.domain.entity.DeviceGroup;
import com.pengsoft.support.biz.facade.TreeEntityFacade;

/**
 * The facade interface of {@link DeviceGroup}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DeviceGroupFacade extends TreeEntityFacade<DeviceGroupService, DeviceGroup, String>, DeviceGroupService {

}
