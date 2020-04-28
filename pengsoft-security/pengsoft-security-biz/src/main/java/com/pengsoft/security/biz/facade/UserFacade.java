package com.pengsoft.security.biz.facade;

import com.pengsoft.security.biz.service.UserService;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.facade.BeanFacade;

/**
 * The facade interface of {@link User}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface UserFacade extends BeanFacade<UserService, User, String>, UserService {}
