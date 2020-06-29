package com.pengsoft.support.test;

import com.pengsoft.support.biz.repository.EntityRepository;

import javax.inject.Inject;

/**
 * The base test superclass for {@link EntityRepository}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class BaseRepositoryTest<R extends EntityRepository<?, ?, ?>> extends BaseTest {

    @Inject
    private R repository;

    public R getRepository() {
        return repository;
    }

}
