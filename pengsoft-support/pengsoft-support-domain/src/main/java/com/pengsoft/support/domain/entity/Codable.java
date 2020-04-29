package com.pengsoft.support.domain.entity;

import java.io.Serializable;

/**
 * Any implementer of this interface must have 'code' field.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface Codable<ID extends Serializable> extends Beanable<ID> {

    /**
     * Returns the value of the entity's 'code' field.
     */
    String getCode();

    /**
     * Set the value of the entity's 'code' field.
     *
     * @param code unique in the same associated entity
     */
    void setCode(String code);

}
