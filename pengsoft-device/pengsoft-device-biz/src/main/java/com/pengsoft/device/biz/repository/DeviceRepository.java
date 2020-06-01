package com.pengsoft.device.biz.repository;

import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.QDevice;
import com.pengsoft.support.biz.repository.BeanRepository;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The repository interface of {@link Device} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface DeviceRepository extends BeanRepository<QDevice, Device, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QDevice root) {
        BeanRepository.super.customize(bindings, root);
        bindings.bind(root.name).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link Device} with given code.
     *
     * @param code {@link Device}'s code
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Device> findOneByCode(@NotBlank String code);

}
