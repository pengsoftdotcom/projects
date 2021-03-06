package com.pengsoft.device.biz.repository;

import com.pengsoft.basedata.biz.repository.OwnedExtRepository;
import com.pengsoft.device.domain.entity.DeviceGroup;
import com.pengsoft.device.domain.entity.QDeviceGroup;
import com.pengsoft.support.biz.repository.TreeEntityRepository;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The repository interface of {@link DeviceGroup} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface DeviceGroupRepository extends TreeEntityRepository<QDeviceGroup, DeviceGroup, String>, OwnedExtRepository {

    @Override
    default void customize(final QuerydslBindings bindings, final QDeviceGroup root) {
        TreeEntityRepository.super.customize(bindings, root);
        bindings.bind(root.name).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link DeviceGroup} with given parent id and name.
     *
     * @param parentId The id of {@link DeviceGroup}'s parent
     * @param name     {@link DeviceGroup}'s name
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<DeviceGroup> findOneByParentIdAndName(String parentId, @NotBlank String name);

}
