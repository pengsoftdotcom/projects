package com.pengsoft.support.biz.facade;

import com.pengsoft.support.biz.service.TreeBeanService;
import com.pengsoft.support.domain.entity.TreeBeanable;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * The implementer of {@link TreeBeanFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class TreeBeanFacadeImpl<S extends TreeBeanService<T, ID>, T extends TreeBeanable<T, ID>, ID extends Serializable> extends BeanFacadeImpl<S, T, ID> implements TreeBeanFacade<S, T, ID> {

    @Override
    public List<T> findAllByParent(final Predicate predicate, final Sort sort) {
        return getService().findAllByParent(predicate, sort);
    }

    @Override
    public List<T> findAllExcludeSelfAndItsChildrenByParent(final T self, final Predicate predicate, final Sort sort) {
        return getService().findAllExcludeSelfAndItsChildrenByParent(self, predicate, sort);
    }

    @Override
    public List<T> findAllExcludeSelfAndItsChildren(final T self, final Predicate predicate, final Sort sort) {
        return getService().findAllExcludeSelfAndItsChildren(self, predicate, sort);
    }

}
