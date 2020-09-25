package com.pengsoft.device.biz.repository;

import com.pengsoft.device.domain.entity.Product;
import com.pengsoft.device.domain.entity.QProduct;
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
public interface ProductRepository extends EntityRepository<QProduct, Product, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QProduct root) {
        EntityRepository.super.customize(bindings, root);
        bindings.bind(root.name).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link Product} with given code.
     *
     * @param code {@link Product}'s code
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Product> findOneByCode(@NotBlank String code);

    /**
     * Returns an {@link Optional} of a {@link Product} with given category id and name.
     *
     * @param categoryId The id of {@link Product}'s category
     * @param name       {@link Product}'s name
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Product> findOneByCategoryIdAndName(@NotBlank String categoryId, @NotBlank String name);

}
