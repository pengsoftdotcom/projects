package com.pengsoft.system.biz.repository;

import com.pengsoft.support.biz.repository.TreeBeanRepository;
import com.pengsoft.system.domain.entity.QRegion;
import com.pengsoft.system.domain.entity.Region;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The repository interface of {@link Region} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface RegionRepository extends TreeBeanRepository<QRegion, Region, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QRegion root) {
        TreeBeanRepository.super.customize(bindings, root);
        bindings.bind(root.code).first(StringPath::startsWith);
    }

    /**
     * Returns an {@link Optional} of a {@link Region} with given code.
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Region> findOneByCode(@NotBlank String code);

}
