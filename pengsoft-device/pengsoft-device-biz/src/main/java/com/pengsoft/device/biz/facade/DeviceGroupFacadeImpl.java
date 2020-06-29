package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.DeviceGroupService;
import com.pengsoft.device.domain.entity.DeviceGroup;
import com.pengsoft.support.biz.facade.TreeEntityFacadeImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link DeviceGroupFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class DeviceGroupFacadeImpl extends TreeEntityFacadeImpl<DeviceGroupService, DeviceGroup, String> implements DeviceGroupFacade {

    @Override
    public Optional<DeviceGroup> findOneByParentAndName(final DeviceGroup parent, final String name) {
        return getService().findOneByParentAndName(parent, name);
    }

}
