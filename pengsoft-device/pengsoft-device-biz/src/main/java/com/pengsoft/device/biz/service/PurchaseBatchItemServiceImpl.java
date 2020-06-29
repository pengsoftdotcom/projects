package com.pengsoft.device.biz.service;

import com.pengsoft.device.biz.repository.DeviceConnectionLogRepository;
import com.pengsoft.device.biz.repository.DeviceRepository;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.DeviceConnectionLog;
import com.pengsoft.support.biz.service.BeanServiceImpl;
import com.pengsoft.support.commons.util.DateUtils;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
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

    @Inject
    private DeviceConnectionLogRepository deviceConnectionLogRepository;

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
    public void activate(final String code) {
        final var device = findOneByCode(code).orElseThrow(() -> getExceptions().entityNotFound(code));
        device.setActivated(true);
        device.setActivatedAt(DateUtils.currentDateTime());
        super.save(device);
    }

    @Override
    public void online(final Device device) {
        device.setOnline(true);
        super.save(device);
        final var log = new DeviceConnectionLog();
        log.setDevice(device);
        log.setOnline(true);
        deviceConnectionLogRepository.save(log);
    }

    @Override
    public void offline(final Device device) {
        device.setOnline(false);
        super.save(device);
        final var log = new DeviceConnectionLog();
        log.setDevice(device);
        log.setOnline(false);
        deviceConnectionLogRepository.save(log);
    }

    @Override
    public Optional<Device> findOneByCode(final String code) {
        return getRepository().findOneByCode(code);
    }

}
