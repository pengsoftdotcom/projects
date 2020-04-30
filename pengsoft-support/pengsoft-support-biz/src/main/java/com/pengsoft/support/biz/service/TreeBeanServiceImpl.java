package com.pengsoft.support.biz.service;

import com.pengsoft.support.biz.repository.TreeBeanRepository;
import com.pengsoft.support.biz.util.QueryDslUtils;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.domain.entity.TreeBeanable;
import com.pengsoft.support.domain.util.EntityUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.List;

/**
 * The implementer of {@link TreeBeanService} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class TreeBeanServiceImpl<R extends TreeBeanRepository<?, T, ID>, T extends TreeBeanable<T, ID>, ID extends Serializable>
        extends BeanServiceImpl<R, T, ID> implements TreeBeanService<T, ID> {

    @Override
    public T save(final T target) {
        final T source = target.getId() == null ? null : findOne(target.getId()).orElse(null);
        if (source != null) {
            target.setChildren(source.getChildren());
            // check the original parent if is a leaf node.
            if (EntityUtils.ne(source.getParent(), target.getParent()) && source.getParent() != null) {
                source.getParent().setLeaf(source.getParent().getChildren().size() == 1);
                super.save(source.getParent());
            }
        }

        if (target.getParent() != null) {
            // set the current parent as a non-leaf node.
            if (EntityUtils.isNotPersisted(target.getParent())) {
                target.setParent(findOne(target.getParent().getId()).orElseThrow(() -> newInstanceOfEntityNotFoundException(target.getParent().getId())));
            }
            target.getParent().getChildren().add(target);
            target.getParent().setLeaf(false);
            super.save(target.getParent());

            // change parent ids and depth.
            if (target.getParent().getDepth() == 1) {
                target.setParentIds(target.getParent().getId().toString());
                target.setDepth(2);
            } else {
                target.setParentIds(StringUtils.join(new Object[]{target.getParent().getParentIds(), target.getParent().getId()}, StringUtils.GLOBAL_SEPARATOR));
                target.setDepth(target.getParent().getDepth() + 1);
            }
        }
        super.save(target);

        // change parent ids and depth of children.
        if (target.getId() != null) {
            final var deque = new ArrayDeque<>(target.getChildren());
            var parent = target;
            while (!deque.isEmpty()) {
                final T child = deque.pop();
                child.setParentIds(StringUtils.join(new Object[]{parent.getParentIds(), parent.getId()}, StringUtils.GLOBAL_SEPARATOR));
                child.setDepth(parent.getDepth() + 1);
                super.save(child);
                parent = child;
                deque.addAll(parent.getChildren());
            }
        }

        return target;
    }

    @Override
    public void delete(final T bean) {
        findOne(bean.getId()).ifPresent(node -> {
            super.delete(node);
            if (node.getParent() != null) {
                findOne(node.getParent().getId()).ifPresent(parent -> {
                    parent.getChildren().removeIf(child -> EntityUtils.eq(child, node));
                    parent.setLeaf(parent.getChildren().isEmpty());
                    super.save(parent);
                });
            }
        });
    }

    @Override
    public List<T> findAllByParent(final Predicate predicate, final Sort sort) {
        return findAll(QueryDslUtils.byParent(predicate, getEntityClass()), getDefaultSort());
    }

    @Override
    public List<T> findAllExcludeSelfAndItsChildrenByParent(final T self, final Predicate predicate, final Sort sort) {
        return findAllByParent(getPredicateOfExcludingSelfAndItsChildren(self, predicate), sort);
    }

    private Predicate getPredicateOfExcludingSelfAndItsChildren(final T self, Predicate predicate) {
        if (self != null) {
            final var parentIds = (StringPath) QueryDslUtils.getPath(getEntityClass(), "parentIds");
            predicate = QueryDslUtils.merge(parentIds.notLike(self.getParentIds() + "%"), predicate);
        }
        return predicate;
    }

    @Override
    public List<T> findAllExcludeSelfAndItsChildren(final T self, final Predicate predicate, final Sort sort) {
        return findAll(getPredicateOfExcludingSelfAndItsChildren(self, predicate), sort);
    }

}
