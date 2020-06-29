package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.DeviceConfigService;
import com.pengsoft.device.domain.entity.DeviceConfig;
import com.pengsoft.support.biz.facade.EntityFacade;

/**
 * The facade interface of {@link DeviceConfig}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DeviceConfigFacade extends EntityFacade<DeviceConfigService, DeviceConfig, String>, DeviceConfigService {

}
