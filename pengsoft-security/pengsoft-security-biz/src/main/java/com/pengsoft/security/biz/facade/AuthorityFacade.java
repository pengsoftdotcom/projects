package com.pengsoft.security.biz.facade;

import com.pengsoft.security.biz.service.AuthorityService;
import com.pengsoft.security.domain.entity.Authority;
import com.pengsoft.support.biz.facade.BeanFacade;

/**
 * The facade interface of {@link Authority}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface AuthorityFacade extends BeanFacade<AuthorityService, Authority, String>, AuthorityService {
}
