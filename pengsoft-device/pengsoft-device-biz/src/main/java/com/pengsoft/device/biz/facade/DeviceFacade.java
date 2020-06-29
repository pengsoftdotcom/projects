package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.DeviceService;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.support.biz.facade.EntityFacade;

/**
 * The facade interface of {@link Device}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DeviceFacade extends EntityFacade<DeviceService, Device, String>, DeviceService {

}
