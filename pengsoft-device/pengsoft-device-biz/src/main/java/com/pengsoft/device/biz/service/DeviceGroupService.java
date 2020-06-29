package com.pengsoft.device.biz.service;

import com.pengsoft.device.domain.entity.DeviceGroup;
import com.pengsoft.support.biz.service.TreeEntityService;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The service interface of {@link DeviceGroup}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DeviceGroupService extends TreeEntityService<DeviceGroup, String> {

    /**
     * Returns an {@link Optional} of a {@link DeviceGroup} with given parent and name.
     *
     * @param parent {@link DeviceGroup}'s parent
     * @param name   {@link DeviceGroup}'s name
     */
    Optional<DeviceGroup> findOneByParentAndName(DeviceGroup parent, @NotBlank String name);

}
