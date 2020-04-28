package com.pengsoft.support.test;

import com.pengsoft.support.biz.service.BeanService;

import javax.inject.Inject;

/**
 * The base test superclass for {@link BeanService}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class BaseServiceTest<S extends BeanService<?, ?>> extends BaseTest {

    @Inject
    private S service;

    public S getService() {
        return service;
    }

}
