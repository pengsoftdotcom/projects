package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.DeviceConnectionLogService;
import com.pengsoft.device.domain.entity.DeviceConnectionLog;
import com.pengsoft.support.biz.facade.EntityFacade;

/**
 * The facade interface of {@link DeviceConnectionLog}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DeviceConnectionLogFacade extends EntityFacade<DeviceConnectionLogService, DeviceConnectionLog, String>, DeviceConnectionLogService {

}
