package com.pengsoft.support.biz.api;

import com.pengsoft.support.biz.facade.TreeBeanFacade;
import com.pengsoft.support.domain.entity.TreeBeanable;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * The web api of {@link TreeBeanable}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class TreeBeanApi<F extends TreeBeanFacade<?, T, ID>, T extends TreeBeanable<T, ID>, ID extends Serializable> extends BeanApi<F, T, ID> {

    @GetMapping("find-all-by-parent")
    public List<T> findAllByParent(final Predicate predicate, final Sort sort) {
        return getFacade().findAllByParent(predicate, sort);
    }

    @GetMapping("find-all-exclude-self-and-its-children-by-parent")
    public List<T> findAllExcludeSelfAndSelfChildrenByParent(@RequestParam(value = "self.id", required = false) final T self, final Predicate predicate, final Sort sort) {
        return getFacade().findAllExcludeSelfAndItsChildrenByParent(self, predicate, sort);
    }

    @GetMapping("find-all-exclude-self-and-its-children")
    public List<T> findAllExcludeSelfAndSelfChildren(@RequestParam(value = "self.id", required = false) final T self, final Predicate predicate, final Sort sort) {
        return getFacade().findAllExcludeSelfAndItsChildren(self, predicate, sort);
    }

}
