package com.pengsoft.device.biz.repository;

import com.pengsoft.device.domain.entity.Product;
import com.pengsoft.device.domain.entity.ProductConfig;
import com.pengsoft.device.domain.entity.QProductConfig;
import com.pengsoft.support.biz.repository.EntityRepository;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The repository interface of {@link Product} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface ProductConfigRepository extends EntityRepository<QProductConfig, ProductConfig, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QProductConfig root) {
        EntityRepository.super.customize(bindings, root);
        bindings.bind(root.code).first(StringPath::contains);
        bindings.bind(root.name).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link ProductConfig} with given product id and code.
     *
     * @param productId The id of {@link ProductConfig}'s product
     * @param code      {@link ProductConfig}'s code
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<ProductConfig> findOneByProductIdAndCode(@NotBlank String productId, @NotBlank String code);

    /**
     * Returns an {@link Optional} of a {@link ProductConfig} with given product id and name.
     *
     * @param productId The id of {@link ProductConfig}'s product
     * @param name      {@link ProductConfig}'s name
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<ProductConfig> findOneByProductIdAndName(@NotBlank String productId, @NotBlank String name);

}
