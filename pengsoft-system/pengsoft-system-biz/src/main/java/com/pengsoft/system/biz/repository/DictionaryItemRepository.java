package com.pengsoft.system.biz.repository;

import com.pengsoft.support.biz.repository.TreeEntityRepository;
import com.pengsoft.system.domain.entity.DictionaryItem;
import com.pengsoft.system.domain.entity.DictionaryType;
import com.pengsoft.system.domain.entity.QDictionaryItem;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * The repository interface of {@link DictionaryItem} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface DictionaryItemRepository extends TreeEntityRepository<QDictionaryItem, DictionaryItem, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QDictionaryItem root) {
        TreeEntityRepository.super.customize(bindings, root);
        bindings.bind(root.code).first(StringPath::contains);
        bindings.bind(root.name).first(StringPath::contains);
    }

    /**
     * Returns a collection of a {@link DictionaryItem} with given code.
     *
     * @param code {@link DictionaryType}'s code
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    List<DictionaryItem> findAllByTypeCode(@NotBlank String code);

    /**
     * Returns an {@link Optional} of a {@link DictionaryItem} with given {@linkplain DictionaryType type}, {@linkplain DictionaryItem parent} and code.
     *
     * @param type   {@link DictionaryType}
     * @param parent The parent {@link DictionaryItem}
     * @param code   {@link DictionaryType}'s code
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<DictionaryItem> findOneByTypeAndParentAndCode(@NotNull DictionaryType type, DictionaryItem parent, @NotBlank String code);

}
