package com.pengsoft.device.biz.repository;

import com.pengsoft.device.domain.entity.PurchaseBatchItem;
import com.pengsoft.device.domain.entity.QPurchaseBatchItem;
import com.pengsoft.support.biz.repository.BeanRepository;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

/**
 * The repository interface of {@link PurchaseBatchItem} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface PurchaseBatchItemRepository extends BeanRepository<QPurchaseBatchItem, PurchaseBatchItem, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QPurchaseBatchItem root) {
        BeanRepository.super.customize(bindings, root);
        bindings.bind(root.batch.name).first(StringPath::contains);
    }

}
