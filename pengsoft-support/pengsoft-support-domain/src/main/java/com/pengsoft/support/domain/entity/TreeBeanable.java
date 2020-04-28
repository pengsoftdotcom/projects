package com.pengsoft.support.domain.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Basic tree entity interface
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface TreeBeanable<T extends TreeBeanable<T, ID>, ID extends Serializable> extends Beanable<ID> {

    /**
     * Get the value of the entity's parent field.
     */
    T getParent();

    /**
     * Set the value of the entity's parent field.
     *
     * @param parent parent node
     */
    void setParent(final T parent);

    /**
     * Get the value of the entity's parentIds field.
     */
    String getParentIds();

    /**
     * Set the value of the entity's parentIds field.
     *
     * @param parentIds All parent id joining with comma.
     */
    void setParentIds(String parentIds);

    /**
     * Get the value of the entity's children field.
     */
    List<T> getChildren();

    /**
     * Set the value of the entity's children field.
     *
     * @param children child nodes
     */
    void setChildren(final List<T> children);

    /**
     * Get the value of the entity's leaf field.
     */
    boolean isLeaf();

    /**
     * Set the value of the entity's leaf field.
     *
     * @param leaf whether it is a leaf node
     */
    void setLeaf(final boolean leaf);

    /**
     * Get the value of the entity's depth field.
     */
    long getDepth();

    /**
     * Set the value of the entity's leaf field.
     *
     * @param depth the depth of the tree, the default value is 1.
     */
    void setDepth(final long depth);

}
