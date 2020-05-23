package com.pengsoft.support.biz.service;

import com.google.common.collect.Lists;
import com.pengsoft.support.biz.repository.BeanRepository;
import com.pengsoft.support.commons.exception.Exceptions;
import com.pengsoft.support.domain.entity.Beanable;
import com.pengsoft.support.domain.entity.Codeable;
import com.pengsoft.support.domain.entity.Sortable;
import com.querydsl.core.types.Predicate;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static com.pengsoft.support.commons.util.ClassUtils.getGenericType;

/**
 * The implementer of {@link BeanService} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class BeanServiceImpl<R extends BeanRepository<?, T, ID>, T extends Beanable<ID>, ID extends Serializable>
        implements BeanService<T, ID> {

    @Inject
    protected Exceptions exceptions;

    @Inject
    private R repository;

    @Inject
    private MessageSource messageSource;

    public R getRepository() {
        return repository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<T> getEntityClass() {
        return (Class<T>) getGenericType(getClass(), 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<ID> getIdClass() {
        return (Class<ID>) getGenericType(getClass(), 2);
    }

    @Override
    public T save(final T bean) {
        return repository.save(bean);
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
    public void delete(final T bean) {
        repository.delete(bean);
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
