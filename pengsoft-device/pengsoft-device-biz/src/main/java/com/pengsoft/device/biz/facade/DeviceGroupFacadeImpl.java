package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.GroupService;
import com.pengsoft.device.domain.entity.DeviceGroup;
import com.pengsoft.support.biz.facade.TreeEntityFacadeImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link GroupFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class GroupFacadeImpl extends TreeEntityFacadeImpl<GroupService, DeviceGroup, String> implements GroupFacade {

    @Override
    public Optional<DeviceGroup> findOneByParentAndName(final DeviceGroup parent, final String name) {
        return getService().findOneByParentAndName(parent, name);
    }

}
