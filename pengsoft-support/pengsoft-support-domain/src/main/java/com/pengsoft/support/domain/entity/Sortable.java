package com.pengsoft.support.domain.entity;

/**
 * Any implementer of this interface must have 'sequence' field.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface Sortable {

    /**
     * Returns the value of the entity's 'sequence' field.
     */
    long getSequence();

    /**
     * Set the value of the entity's 'sequence' field.
     *
     * @param sequence The weight of the entity.
     */
    void setSequence(long sequence);

}
