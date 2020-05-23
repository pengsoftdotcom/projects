package com.pengsoft.system.biz.repository;

import com.pengsoft.support.biz.repository.BeanRepository;
import com.pengsoft.system.domain.entity.DictionaryType;
import com.pengsoft.system.domain.entity.QDictionaryType;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The repository interface of {@link DictionaryType} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface DictionaryTypeRepository extends BeanRepository<QDictionaryType, DictionaryType, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QDictionaryType root) {
        BeanRepository.super.customize(bindings, root);
        bindings.bind(root.code).first(StringPath::contains);
        bindings.bind(root.name).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link DictionaryType} with given code.
     *
     * @param code {@link DictionaryType}'code
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<DictionaryType> findOneByCode(@NotBlank String code);

}
