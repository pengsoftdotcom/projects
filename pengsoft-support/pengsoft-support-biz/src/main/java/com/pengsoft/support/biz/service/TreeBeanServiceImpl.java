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
        var parentChanged = false;
        final T source = target.getId() == null ? null : findOne(target.getId()).orElse(null);
        if (source != null) {
            target.setChildren(source.getChildren());
            parentChanged = EntityUtils.ne(source.getParent(), target.getParent());
            // check the original parent if is a leaf node.
            if (parentChanged && source.getParent() != null) {
                source.getParent().setLeaf(source.getParent().getChildren().size() == 1);
                super.save(source.getParent());
            }
        }

        if (target.getParent() != null) {
            // set the current parent as a non-leaf node.
            if (EntityUtils.isNotPersisted(target.getParent())) {
                target.setParent(
                        findOne(target.getParent().getId()).orElseThrow(() -> getExceptions().entityNotFound(target.getParent().getId().toString())));
            }
            target.getParent().getChildren().add(target);
            target.getParent().setLeaf(false);
            super.save(target.getParent());

            // change parent ids and depth.
            if (StringUtils.isBlank(target.getParent().getParentIds())) {
                target.setParentIds(target.getParent().getId().toString());
            } else {
                target.setParentIds(StringUtils.join(new Object[] { target.getParent().getParentIds(), target.getParent().getId() },
                        StringUtils.GLOBAL_SEPARATOR));
            }
            target.setDepth(target.getParent().getDepth() + 1);
        }
        super.save(target);

        if (parentChanged) {
            updateTheParentIdsAndDepthOfChildNodes(target);
        }

        return target;
    }

    private void updateTheParentIdsAndDepthOfChildNodes(final T target) {
        final var deque = new ArrayDeque<>(target.getChildren());
        var parent = target;
        while (!deque.isEmpty()) {
            final T child = deque.pop();
            if (StringUtils.isBlank(parent.getParentIds())) {
                child.setParentIds(parent.getId().toString());
            } else {
                child.setParentIds(StringUtils.join(new Object[] { parent.getParentIds(), parent.getId() }, StringUtils.GLOBAL_SEPARATOR));
            }
            child.setDepth(parent.getDepth() + 1);
            super.save(child);
            parent = child;
            deque.addAll(parent.getChildren());
        }
    }

    @Override
    public void delete(final T bean) {
        findOne(bean.getId()).ifPresent(node -> {
            super.delete(node);
            if (node.getParent() != null) {
                final var parent = node.getParent();
                parent.getChildren().removeIf(child -> EntityUtils.eq(child, node));
                parent.setLeaf(parent.getChildren().isEmpty());
                super.save(parent);
            }
        });
    }

    @Override
    public List<T> findAllByParent(final Predicate predicate, final Sort sort) {
        return findAll(QueryDslUtils.byParent(predicate, getEntityClass()), getDefaultSort());
    }

    @Override
    public List<T> findAllExcludeSelfAndItsChildrenByParent(final T self, final Predicate predicate, final Sort sort) {
        return findAllByParent(getPredicateOfExcludeSelfAndItsChildren(self, predicate), sort);
    }

    private Predicate getPredicateOfExcludeSelfAndItsChildren(final T self, Predicate predicate) {
        if (self != null) {
            final var idPath = QueryDslUtils.getIdStringPath(getEntityClass());
            predicate = QueryDslUtils.merge(idPath.ne(self.getId().toString()), predicate);

            final var parentIdsPath = (StringPath) QueryDslUtils.getPath(getEntityClass(), "parentIds");
            if (StringUtils.isBlank(self.getParentIds())) {
                predicate = QueryDslUtils.merge(parentIdsPath.notLike(self.getId().toString() + "%"), predicate);
            } else {
                predicate = QueryDslUtils.merge(
                        parentIdsPath.notLike(
                                StringUtils.join(new String[] { self.getParentIds(), self.getId().toString() }, StringUtils.GLOBAL_SEPARATOR) + "%"),
                        predicate);
            }
        }
        return predicate;
    }

    @Override
    public List<T> findAllExcludeSelfAndItsChildren(final T self, final Predicate predicate, final Sort sort) {
        return findAll(getPredicateOfExcludeSelfAndItsChildren(self, predicate), sort);
    }

    @Override
    public List<T> findAllByParentIdsStartsWith(final String parentIds) {
        return getRepository().findAllByParentIdsStartsWith(parentIds);
    }

}
