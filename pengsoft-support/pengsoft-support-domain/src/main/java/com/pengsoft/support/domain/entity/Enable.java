package com.pengsoft.support.domain.entity;

/**
 * Any implementer of this interface must have 'enabled' field.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface Enable {

    /**
     * Returns the value of the entity's 'enabled' field.
     */
    boolean isEnabled();

    /**
     * Set the value of the entity's 'enabled' field.
     *
     * @param enabled Whether the entity is enabled.
     */
    void setEnabled(boolean enabled);
}
