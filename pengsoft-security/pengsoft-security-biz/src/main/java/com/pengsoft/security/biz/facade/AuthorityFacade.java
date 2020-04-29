package com.pengsoft.security.biz.facade;

import com.pengsoft.security.biz.service.AuthorityService;
import com.pengsoft.security.domain.entity.Authority;
import com.pengsoft.support.biz.facade.BeanFacade;
import com.pengsoft.support.domain.entity.Beanable;

import java.io.Serializable;
import java.util.List;

/**
 * The facade interface of {@link Authority}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface AuthorityFacade extends BeanFacade<AuthorityService, Authority, String>, AuthorityService {

    /**
     * Save the authorities of entity admin.
     *
     * @param entityClass The entity class
     * @return The authorities of entity admin.
     */
    List<Authority> saveEntityAdminAuthorities(Class<? extends Beanable<? extends Serializable>> entityClass);

}
