package com.pengsoft.system.biz.repository;

import com.pengsoft.support.biz.repository.TreeEntityRepository;
import com.pengsoft.system.domain.entity.QRegion;
import com.pengsoft.system.domain.entity.Region;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

/**
 * The repository interface of {@link Region} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface RegionRepository extends TreeEntityRepository<QRegion, Region, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QRegion root) {
        TreeEntityRepository.super.customize(bindings, root);
        bindings.bind(root.code).first(StringPath::startsWith);
        bindings.bind(root.name).first(StringPath::startsWith);
    }

    /**
     * Returns an {@link Optional} of a {@link Region} with given code.
     *
     * @param code {@link Region}'code
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Region> findOneByCode(@NotBlank String code);

    /**
     * Returns an {@link Optional} of a {@link Region} with property 'index' is not null.
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    List<Region> findAllByIndexIsNotNullOrderByIndexAscCodeAsc();

}
