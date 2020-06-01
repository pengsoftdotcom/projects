package com.pengsoft.support.biz.facade;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.pengsoft.support.biz.service.BeanService;
import com.pengsoft.support.domain.entity.Beanable;
import com.querydsl.core.types.Predicate;

/**
 * The implementer of {@link BeanFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class BeanFacadeImpl<S extends BeanService<T, ID>, T extends Beanable<ID>, ID extends Serializable> implements BeanFacade<S, T, ID> {

    @Inject
    private S service;

    @Override
    public S getService() {
        return service;
    }

    @Override
    public Class<T> getEntityClass() {
        return service.getEntityClass();
    }

    @Override
    public Class<ID> getIdClass() {
        return service.getIdClass();
    }

    @Override
    public T save(final T bean) {
        return service.save(bean);
    }

    @Override
    public List<T> save(final List<T> beans) {
        return service.save(beans);
    }

    @Override
    public void delete(final T bean) {
        service.delete(bean);
    }

    @Override
    public void delete(final List<T> beans) {
        service.delete(beans);
    }

    @Override
    public Optional<T> findOne(final ID id) {
        return service.findOne(id);
    }

    @Override
    public Optional<T> findOne(final Predicate predicate) {
        return service.findOne(predicate);
    }

    @Override
    public Page<T> findPage(final Predicate predicate, final Pageable pageable) {
        return service.findPage(predicate, pageable);
    }

    @Override
    public List<T> findAll(final Predicate predicate, final Sort sort) {
        return service.findAll(predicate, sort);
    }

    @Override
    public boolean exists(final Predicate predicate) {
        return service.exists(predicate);
    }

    @Override
    public long count(final Predicate predicate) {
        return service.count(predicate);
    }

}
