package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.GroupService;
import com.pengsoft.device.domain.entity.DeviceGroup;
import com.pengsoft.support.biz.facade.EntityFacade;

/**
 * The facade interface of {@link DeviceGroup}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface GroupFacade extends EntityFacade<GroupService, DeviceGroup, String>, GroupService {

}
