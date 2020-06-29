package com.pengsoft.device.biz.repository;

import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.DeviceConnectionLog;
import com.pengsoft.device.domain.entity.QDeviceConnectionLog;
import com.pengsoft.support.biz.repository.EntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotNull;

/**
 * The repository interface of {@link Device} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface DeviceConnectionLogRepository extends EntityRepository<QDeviceConnectionLog, DeviceConnectionLog, String> {

    /**
     * Returns a {@link Page} of devices with given device.
     *
     * @param device {@link DeviceConnectionLog}'s device
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Page<DeviceConnectionLog> findPageByDeviceOrderByCreatedAtDesc(@NotNull Device device, Pageable pageable);

}
