package com.pengsoft.support.biz.api;

import com.pengsoft.support.biz.facade.BeanFacade;
import com.pengsoft.support.biz.service.EnableService;
import com.pengsoft.support.biz.service.SortService;
import com.pengsoft.support.biz.util.QueryDslUtils;
import com.pengsoft.support.domain.entity.Beanable;
import com.pengsoft.support.domain.entity.Enable;
import com.pengsoft.support.domain.entity.Sortable;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The web api of {@link Beanable}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class BeanApi<F extends BeanFacade<?, T, ID>, T extends Beanable<ID>, ID extends Serializable> {

    @Inject
    private F facade;

    @Inject
    private EnableService enableService;

    @Inject
    private SortService sortService;

    public F getFacade() {
        return facade;
    }

    public SortService getSortService() {
        return sortService;
    }

    @PostMapping("save")
    public T save(@RequestBody final T bean) {
        return facade.save(bean);
    }

    @DeleteMapping("delete")
    public void delete(final Predicate predicate) {
        facade.delete(facade.findAll(predicate, Sort.unsorted()));
    }

    @PutMapping("enable")
    public void enable(@RequestParam("id") final List<T> enables) {
        final var domainClass = facade.getEntityClass();
        Assert.isTrue(Enable.class.isAssignableFrom(domainClass), domainClass.getName() + " is not a Enable");
        enableService.enable(enables.stream().map(enable -> (Enable) enable).collect(Collectors.toList()));
    }

    @PutMapping("disable")
    public void disable(@RequestParam("id") final List<T> enables) {
        final var domainClass = facade.getEntityClass();
        Assert.isTrue(Enable.class.isAssignableFrom(domainClass), domainClass.getName() + " is not a Enable");
        enableService.disable(enables.stream().map(enable -> (Enable) enable).collect(Collectors.toList()));
    }

    @PutMapping("sort")
    public void sort(@RequestBody final Map<ID, Long> sortInfo) {
        final var entityClass = facade.getEntityClass();
        Assert.isTrue(Sortable.class.isAssignableFrom(entityClass), entityClass.getName() + " is not a Sortable");
        final Predicate predicate;
        final var idClass = getFacade().getIdClass();
        if (String.class.equals(idClass)) {
            predicate = QueryDslUtils.getIdStringPath(entityClass).in(sortInfo.keySet().stream().map(id -> (String) id).collect(Collectors.toList()));
        } else if (Long.class.equals(idClass)) {
            predicate = QueryDslUtils.getIdNumberPath(entityClass).in(sortInfo.keySet().stream().map(id -> (Long) id).collect(Collectors.toList()));
        } else {
            throw new NotImplementedException("not implemented for id class: " + idClass.getName());
        }
        final var beans = facade.findAll(predicate, Sort.unsorted());
        sortService.sort(beans.stream().collect(Collectors.toMap(sortable -> (Sortable) sortable, sortable -> sortInfo.get(sortable.getId()))));
    }

    @GetMapping("find-one")
    public T findOne(final Predicate predicate) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
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
