package com.pengsoft.support.test;

import com.pengsoft.support.biz.service.EntityService;

import javax.inject.Inject;

/**
 * The base test superclass for {@link EntityService}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class BaseServiceTest<S extends EntityService<?, ?>> extends BaseTest {

    @Inject
    private S service;

    public S getService() {
        return service;
    }

}
