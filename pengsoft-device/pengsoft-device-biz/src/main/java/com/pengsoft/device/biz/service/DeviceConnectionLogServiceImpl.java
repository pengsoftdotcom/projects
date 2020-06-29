package com.pengsoft.device.biz.service;

import com.pengsoft.device.biz.repository.DeviceConnectionLogRepository;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.DeviceConnectionLog;
import com.pengsoft.support.biz.service.BeanServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link DeviceConnectionLogService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class DeviceConnectionLogServiceImpl extends BeanServiceImpl<DeviceConnectionLogRepository, DeviceConnectionLog, String> implements DeviceConnectionLogService {

    @Override
    public Page<DeviceConnectionLog> findPageByDevice(final Device device, final Pageable pageable) {
        return getRepository().findPageByDeviceOrderByCreatedAtDesc(device, pageable);
    }

}
