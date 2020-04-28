package com.pengsoft.support.test;

import javax.inject.Inject;
import javax.validation.Validator;

/**
 * The base test superclass for entities.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class BaseEntityTest extends BaseTest {

    @Inject
    private Validator validator;

    public Validator getValidator() {
        return validator;
    }

}
