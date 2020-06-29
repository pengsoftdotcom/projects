package com.pengsoft.support.biz.api;

import com.pengsoft.support.biz.facade.TreeEntityFacade;
import com.pengsoft.support.domain.entity.TreeEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * The web api of {@link TreeEntity}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class TreeEntityApi<F extends TreeEntityFacade<?, T, ID>, T extends TreeEntity<T, ID>, ID extends Serializable> extends EntityApi<F, T, ID> {

    @GetMapping("find-all-by-parent")
    public List<T> findAllByParent(final Predicate predicate, final Sort sort) {
        return getFacade().findAllByParent(predicate, sort);
    }

    @GetMapping("find-all-exclude-self-and-its-children-by-parent")
    public List<T> findAllExcludeSelfAndItsChildrenByParent(@RequestParam(value = "self.id", required = false) final T self, final Predicate predicate, final Sort sort) {
        return getFacade().findAllExcludeSelfAndItsChildrenByParent(self, predicate, sort);
    }

    @GetMapping("find-all-exclude-self-and-its-children")
    public List<T> findAllExcludeSelfAndItsChildren(@RequestParam(value = "self.id", required = false) final T self, final Predicate predicate, final Sort sort) {
        return getFacade().findAllExcludeSelfAndItsChildren(self, predicate, sort);
    }

}
