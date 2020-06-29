package com.pengsoft.support.biz.service;

import com.pengsoft.support.domain.entity.TreeBean;
import com.pengsoft.support.domain.entity.TreeBeanable;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * The service interface of {@link TreeBean}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface TreeBeanService<T extends TreeBeanable<T, ID>, ID extends Serializable> extends BeanService<T, ID> {

    /**
     * Returns all entities under the parent specified in {@link Predicate}
     *
     * @param predicate {@link Predicate}, contains parent parameter, if not parent is null.
     * @param sort      {@link Sort}
     */
    List<T> findAllByParent(Predicate predicate, Sort sort);

    /**
     * Returns all entities under the parent specified in {@link Predicate}, excluding the given node and its children
     * nodes.
     *
     * @param self      The excluded node.
     * @param predicate {@link Predicate}, contains parent parameter, if not parent is null.
     * @param sort      {@link Sort}
     */
    List<T> findAllExcludeSelfAndItsChildrenByParent(T self, Predicate predicate, Sort sort);

    /**
     * Returns all entities, excluding the given node and its children nodes.
     *
     * @param self      The excluded node.
     * @param predicate {@link Predicate}, contains parent parameter, if not parent is null.
     * @param sort      {@link Sort}
     */
    List<T> findAllExcludeSelfAndItsChildren(T self, Predicate predicate, Sort sort);

    /**
     * Returns all entities that their 'parentIds' starts with the given value.
     *
     * @param parentIds The entity's parentIds
     */
    List<T> findAllByParentIdsStartsWith(String parentIds);

}
