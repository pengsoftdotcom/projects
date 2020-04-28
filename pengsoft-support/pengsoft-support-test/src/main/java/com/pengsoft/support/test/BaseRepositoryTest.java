package com.pengsoft.support.test;

import com.pengsoft.support.biz.repository.BeanRepository;

import javax.inject.Inject;

/**
 * The base test superclass for {@link BeanRepository}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class BaseRepositoryTest<R extends BeanRepository<?, ?, ?>> extends BaseTest {

    @Inject
    private R repository;

    public R getRepository() {
        return repository;
    }

}
