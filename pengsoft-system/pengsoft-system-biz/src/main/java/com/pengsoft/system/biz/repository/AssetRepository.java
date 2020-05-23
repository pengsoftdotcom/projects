package com.pengsoft.system.biz.repository;

import com.pengsoft.support.biz.repository.BeanRepository;
import com.pengsoft.system.domain.entity.Asset;
import com.pengsoft.system.domain.entity.QAsset;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

/**
 * The repository interface of {@link Asset} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface AssetRepository extends BeanRepository<QAsset, Asset, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QAsset root) {
        BeanRepository.super.customize(bindings, root);
        bindings.bind(root.originalName).first(StringPath::contains);
        bindings.bind(root.contentLength).first(NumberPath::loe);
    }

}
