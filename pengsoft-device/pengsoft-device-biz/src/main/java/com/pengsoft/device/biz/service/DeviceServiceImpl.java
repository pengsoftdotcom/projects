package com.pengsoft.device.biz.service;

import com.pengsoft.device.biz.repository.DeviceRepository;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.support.biz.service.BeanServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link DeviceService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class DeviceServiceImpl extends BeanServiceImpl<DeviceRepository, Device, String> implements DeviceService {

    @Override
    public Device save(final Device device) {
        findOneByCode(device.getCode()).ifPresent(source -> {
            if (EntityUtils.ne(source, device)) {
                throw getExceptions().constraintViolated("code", "Exists", device.getCode());
            }
        });
        return super.save(device);
    }

    @Override
    public Optional<Device> findOneByCode(final String code) {
        return getRepository().findOneByCode(code);
    }

}
