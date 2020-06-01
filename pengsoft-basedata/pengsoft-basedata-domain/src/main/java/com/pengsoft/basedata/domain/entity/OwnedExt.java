package com.pengsoft.basedata.domain.entity;

import com.pengsoft.security.domain.entity.Ownable;

/**
 * Ownable extension
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface OwnableExt extends Ownable {

    /**
     * Returns the value of the entity's 'controlledBy' field.
     */
    String getControlledBy();

    /**
     * Set the value of the entity's 'controlledBy' field.
     *
     * @param controlledBy The owner({@link Department}) id of the entity.
     */
    void setControlledBy(String controlledBy);

    /**
     * Returns the value of the entity's 'belongsTo' field.
     */
    String getBelongsTo();

    /**
     * Set the value of the entity's 'belongsTo' field.
     *
     * @param belongsTo The owner({@link Organization}) id of the entity.
     */
    void setBelongsTo(String belongsTo);

}
