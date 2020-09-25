package com.pengsoft.device.biz.repository;

import com.pengsoft.device.domain.entity.DeviceConfig;
import com.pengsoft.device.domain.entity.QDeviceConfig;
import com.pengsoft.support.biz.repository.EntityRepository;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The repository interface of {@link DeviceConfig} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface DeviceConfigRepository extends EntityRepository<QDeviceConfig, DeviceConfig, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QDeviceConfig root) {
        EntityRepository.super.customize(bindings, root);
        bindings.bind(root.code).first(StringPath::contains);
        bindings.bind(root.name).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link DeviceConfig} with given device id and code.
     *
     * @param deviceId The id of {@link DeviceConfig}'s device
     * @param code     {@link DeviceConfig}'s code
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<DeviceConfig> findOneByDeviceIdAndCode(@NotBlank String deviceId, @NotBlank String code);

    /**
     * Returns an {@link Optional} of a {@link DeviceConfig} with given device id and name.
     *
     * @param deviceId The id of {@link DeviceConfig}'s device
     * @param name     {@link DeviceConfig}'s name
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<DeviceConfig> findOneByDeviceIdAndName(@NotBlank String deviceId, @NotBlank String name);

}
