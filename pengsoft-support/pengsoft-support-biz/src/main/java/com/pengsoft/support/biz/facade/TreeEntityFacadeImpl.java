package com.pengsoft.support.biz.facade;

import com.pengsoft.support.biz.service.TreeEntityService;
import com.pengsoft.support.domain.entity.TreeEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * The implementer of {@link TreeEntityFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class TreeEntityFacadeImpl<S extends TreeEntityService<T, ID>, T extends TreeEntity<T, ID>, ID extends Serializable> extends EntityFacadeImpl<S, T, ID> implements TreeEntityFacade<S, T, ID> {

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

    @Override
    public List<T> findAllByParentIdsStartsWith(final String parentIds) {
        return getService().findAllByParentIdsStartsWith(parentIds);
    }

}
