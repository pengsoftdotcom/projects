package com.pengsoft.security.biz.facade;

import com.pengsoft.security.biz.service.AuthorityService;
import com.pengsoft.security.domain.entity.Authority;
import com.pengsoft.support.biz.facade.EntityFacade;
import com.pengsoft.support.domain.entity.Entity;

import java.io.Serializable;

/**
 * The facade interface of {@link Authority}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface AuthorityFacade extends EntityFacade<AuthorityService, Authority, String>, AuthorityService {

    /**
     * Save the authorities of entity admin.
     *
     * @param entityClass The entity class
     */
    void saveEntityAdminAuthorities(Class<? extends Entity<? extends Serializable>> entityClass);

}
