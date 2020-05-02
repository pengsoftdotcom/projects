package com.pengsoft.support.biz.service;

import com.pengsoft.support.domain.entity.Bean;
import com.pengsoft.support.domain.entity.Beanable;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The service interface of {@link Bean}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Validated
public interface BeanService<T extends Beanable<ID>, ID extends Serializable> {

    /**
     * Returns the domain class.
     */
    Class<T> getEntityClass();

    /**
     * Returns the id class.
     */
    Class<ID> getIdClass();

    /**
     * Save one entity.
     *
     * @param bean entity
     * @return the saved entity
     */
    T save(@Valid @NotNull T bean);

    /**
     * Save entities.
     *
     * @param beans entities
     * @return the saved entities
     */
    default List<T> save(@NotEmpty final List<T> beans) {
        return beans.stream().map(this::save).collect(Collectors.toList());
    }

    /**
     * Delete one entity.
     *
     * @param bean 实体
     */
    void delete(@NotNull T bean);

    /**
     * Delete entities.
     *
     * @param beans entities
     */
    default void delete(@NotEmpty final List<T> beans) {
        beans.forEach(this::delete);
    }

    /**
     * Returns an entity with given id.
     *
     * @param id ID
     * @return an {@link Optional} of an entity
     */
    Optional<T> findOne(@NotNull ID id);

    /**
     * Returns an entity with given {@link Predicate}.
     *
     * @param predicate {@link Predicate}
     * @return an {@link Optional} of an entity
     */
    Optional<T> findOne(Predicate predicate);

    /**
     * Returns a {@link Page} of entities with given {@link Predicate}
     *
     * @param predicate {@link Predicate}
     * @param pageable  {@link Pageable}
     * @return a page of entities
     */
    Page<T> findPage(Predicate predicate, Pageable pageable);

    /**
     * Returns all entities with given {@link Predicate}.
     *
     * @param predicate {@link Predicate}
     * @param sort      {@link Sort}
     * @return entities
     */
    List<T> findAll(Predicate predicate, Sort sort);

    /**
     * Returns whether an entity with the given {@link Predicate} exists.
     *
     * @param predicate {@link Predicate}
     * @return If true, exists.
     */
    boolean exists(Predicate predicate);

    /**
     * Returns the number of entities by {@link Predicate}.
     *
     * @param predicate {@link Predicate}
     * @return The number of entities.
     */
    long count(Predicate predicate);

}
