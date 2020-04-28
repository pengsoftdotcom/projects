package com.pengsoft.support.biz.api;

import com.pengsoft.support.biz.facade.BeanFacade;
import com.pengsoft.support.biz.util.QueryDslUtils;
import com.pengsoft.support.domain.entity.Beanable;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * The web api of {@link Beanable}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class BeanApi<F extends BeanFacade<?, T, ID>, T extends Beanable<ID>, ID extends Serializable> {

    @Inject
    private F facade;

    public F getFacade() {
        return facade;
    }

    @PostMapping("save")
    public T save(@RequestBody final T bean) {
        return facade.save(bean);
    }

    @DeleteMapping("delete")
    public void delete(final Predicate predicate) {
        facade.delete(facade.findAll(predicate, Sort.unsorted()));
    }

    @GetMapping("find-one")
    public T findOne(final Predicate predicate) throws Exception {
        Optional<T> optional = Optional.empty();
        if (QueryDslUtils.contains(predicate, getFacade().getEntityClass(), "id")) {
            optional = facade.findOne(predicate);
        }
        return optional.orElse(getFacade().getEntityClass().getDeclaredConstructor().newInstance());
    }

    @GetMapping("find-page")
    public Page<T> findPage(final Predicate predicate, final Pageable pageable) {
        return facade.findPage(predicate, pageable);
    }

    @GetMapping("find-all")
    public List<T> findAll(final Predicate predicate, final Sort sort) {
        return facade.findAll(predicate, sort);
    }

}
