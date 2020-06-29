package com.pengsoft.support.biz.service;

import com.google.common.collect.Lists;
import com.pengsoft.support.biz.repository.EntityRepository;
import com.pengsoft.support.commons.exception.Exceptions;
import com.pengsoft.support.domain.entity.Codeable;
import com.pengsoft.support.domain.entity.Entity;
import com.pengsoft.support.domain.entity.Sortable;
import com.querydsl.core.types.Predicate;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static com.pengsoft.support.commons.util.ClassUtils.getSuperclassGenericType;

/**
 * The implementer of {@link EntityService} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class EntityServiceImpl<R extends EntityRepository<?, T, ID>, T extends Entity<ID>, ID extends Serializable>
        implements EntityService<T, ID> {

    @Getter
    @Inject
    private Exceptions exceptions;

    @Getter
    @Inject
    private R repository;

    @SuppressWarnings("unchecked")
    @Override
    public Class<T> getEntityClass() {
        return (Class<T>) getSuperclassGenericType(getClass(), 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<ID> getIdClass() {
        return (Class<ID>) getSuperclassGenericType(getClass(), 2);
    }

    @Override
    public T save(final T entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<T> findOne(final ID id) {
        return repository.findById(id);
    }

    @Override
    public Page<T> findPage(final Predicate predicate, Pageable pageable) {
        final var sort = pageable.getSort();
        if (sort.isUnsorted()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), getDefaultSort());
        }
        return repository.findAll(predicate, pageable);
    }

    @Override
    public void delete(final T entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<T> findOne(final Predicate predicate) {
        return repository.findOne(predicate);
    }

    @Override
    public List<T> findAll(final Predicate predicate, Sort sort) {
        if (sort == null || sort.isUnsorted()) {
            sort = getDefaultSort();
        }
        return Lists.newArrayList(repository.findAll(predicate, sort));
    }

    /**
     * Returns the default sort.
     */
    protected Sort getDefaultSort() {
        final var entityClass = getEntityClass();
        if (Sortable.class.isAssignableFrom(entityClass)) {
            return Sort.by(Direction.ASC, "sequence");
        } else if (Codeable.class.isAssignableFrom(entityClass)) {
            return Sort.by(Direction.ASC, "code");
        } else {
            return Sort.by(Direction.DESC, "updatedAt");
        }
    }

    @Override
    public boolean exists(final Predicate predicate) {
        return repository.exists(predicate);
    }

    @Override
    public long count(final Predicate predicate) {
        return repository.count(predicate);
    }

}
