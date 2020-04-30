package com.pengsoft.support.biz.service;

import com.pengsoft.support.domain.entity.Enable;

import java.util.List;

/**
 * The enable service interface.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface EnableService {

    /**
     * Set the value of the entity's 'enabled' field to true.
     *
     * @param enable {@link Enable}
     */
    <T extends Enable> void enable(T enable);

    /**
     * Set the value of the all entities' 'enabled' field to true.
     *
     * @param enables A collection of {@link Enable}.
     */
    default <T extends Enable> void enable(final List<T> enables) {
        enables.forEach(this::enable);
    }

    /**
     * Set the value of the entity's 'enabled' field to false.
     *
     * @param enable {@link Enable}
     */
    <T extends Enable> void disable(T enable);

    /**
     * Set the value of the all entities' 'enabled' field to true.
     *
     * @param enables A collection of {@link Enable}.
     */
    default <T extends Enable> void disable(final List<T> enables) {
        enables.forEach(this::disable);
    }

}
