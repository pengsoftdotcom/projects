package com.pengsoft.support.test;

import com.pengsoft.support.biz.facade.BeanFacade;

import javax.inject.Inject;

/**
 * The base test superclass for {@link BeanFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class BaseFacadeTest<F extends BeanFacade<?, ?, ?>> extends BaseTest {

    @Inject
    private F facade;

    public F getFacade() {
        return facade;
    }

}
