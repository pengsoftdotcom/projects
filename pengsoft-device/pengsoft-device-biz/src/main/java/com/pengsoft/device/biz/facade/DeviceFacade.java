package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.DeviceService;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.support.biz.facade.BeanFacade;

/**
 * The facade interface of {@link Device}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DeviceFacade extends BeanFacade<DeviceService, Device, String>, DeviceService {

}
