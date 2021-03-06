package com.pengsoft.system.biz.repository;

import com.pengsoft.security.biz.repository.OwnedRepository;
import com.pengsoft.support.biz.repository.EntityRepository;
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
public interface AssetRepository extends EntityRepository<QAsset, Asset, String>, OwnedRepository {

    @Override
    default void customize(final QuerydslBindings bindings, final QAsset root) {
        EntityRepository.super.customize(bindings, root);
        bindings.bind(root.originalName).first(StringPath::contains);
        bindings.bind(root.contentLength).first(NumberPath::loe);
    }

}
