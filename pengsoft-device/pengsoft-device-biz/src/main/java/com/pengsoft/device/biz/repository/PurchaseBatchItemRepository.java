package com.pengsoft.device.biz.repository;

import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import com.pengsoft.basedata.biz.repository.OwnedExtRepository;
import com.pengsoft.device.domain.entity.PurchaseBatchItem;
import com.pengsoft.device.domain.entity.QPurchaseBatchItem;
import com.pengsoft.support.biz.repository.EntityRepository;
import com.querydsl.core.types.dsl.StringPath;

/**
 * The repository interface of {@link PurchaseBatchItem} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since  1.0.0
 */
@Repository
public interface PurchaseBatchItemRepository extends EntityRepository<QPurchaseBatchItem, PurchaseBatchItem, String>, OwnedExtRepository {

    @Override
    default void customize(final QuerydslBindings bindings, final QPurchaseBatchItem root) {
        EntityRepository.super.customize(bindings, root);
        bindings.bind(root.purchaseBatch.name).first(StringPath::contains);
    }

}
