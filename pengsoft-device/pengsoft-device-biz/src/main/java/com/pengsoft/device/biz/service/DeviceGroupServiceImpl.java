package com.pengsoft.device.biz.service;

import com.pengsoft.device.biz.repository.DeviceGroupRepository;
import com.pengsoft.device.domain.entity.DeviceGroup;
import com.pengsoft.support.biz.service.TreeEntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The implementer of {@link DeviceGroupService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class DeviceGroupServiceImpl extends TreeEntityServiceImpl<DeviceGroupRepository, DeviceGroup, String> implements DeviceGroupService {

    @Override
    public DeviceGroup save(final DeviceGroup deviceGroup) {
        findOneByParentAndName(deviceGroup.getParent(), deviceGroup.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, deviceGroup)) {
                throw getExceptions().constraintViolated("code", "Exists", deviceGroup.getName());
            }
        });
        return super.save(deviceGroup);
    }

    @Override
    public Optional<DeviceGroup> findOneByParentAndName(final DeviceGroup parent, @NotBlank final String name) {
        return getRepository().findOneByParentAndName(parent, name);
    }

}
