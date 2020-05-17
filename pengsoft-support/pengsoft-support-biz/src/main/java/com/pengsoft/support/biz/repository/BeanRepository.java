package com.pengsoft.support.biz.repository;

import java.io.Serializable;
import java.util.Optional;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.validation.annotation.Validated;

import com.pengsoft.support.biz.util.QueryDslUtils;
import com.pengsoft.support.domain.entity.Bean;
import com.pengsoft.support.domain.entity.Beanable;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

/**
 * The repository interface of {@link Bean} based on JPA
 *
 * @author dang.peng@pengsoft.com
 */
@Validated
@NoRepositoryBean
public interface BeanRepository<Q extends EntityPathBase<T>, T extends Beanable<ID>, ID extends Serializable>
        extends JpaRepositoryImplementation<T, ID>, QuerydslPredicateExecutor<T>, QuerydslBinderCustomizer<Q> {

    @Override
    default void customize(final QuerydslBindings bindings, final Q root) {
        final var entityClass = getEntityClass(root);
        final var idClass = getIdClass(root);
        if (idClass.equals(String.class)) {
            final StringPath idPath = QueryDslUtils.getIdStringPath(entityClass);
            bindings.bind(idPath).all((path, value) -> Optional.of(path.in(value)));
        } else if (Number.class.isAssignableFrom(idClass)) {
            final NumberPath<?> idPath = QueryDslUtils.getIdNumberPath(entityClass);
            bindings.bind(idPath).all((path, value) -> Optional.of(path.in(value)));
        }
    }

    default Class<? extends T> getEntityClass(final Q root) {
        return root.getType();
    }

    @SuppressWarnings("unchecked")
    default Class<ID> getIdClass(final Q root) {
        return (Class<ID>) MethodUtils.getMatchingMethod(root.getType(), "getId").getReturnType();
    }

}
