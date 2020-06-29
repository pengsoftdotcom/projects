package com.pengsoft.device.biz.service;

import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.DeviceConnectionLog;
import com.pengsoft.support.biz.service.EntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

/**
 * The service interface of {@link DeviceConnectionLog}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DeviceConnectionLogService extends EntityService<DeviceConnectionLog, String> {

    /**
     * Returns a {@link Page} of devices with given device.
     *
     * @param device {@link DeviceConnectionLog}'s device
     */
    Page<DeviceConnectionLog> findPageByDevice(@NotNull Device device, Pageable pageable);

}
