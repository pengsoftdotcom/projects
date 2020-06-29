package com.pengsoft.security.biz.facade;

import com.pengsoft.security.biz.service.UserService;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.facade.EntityFacade;

/**
 * The facade interface of {@link User}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface UserFacade extends EntityFacade<UserService, User, String>, UserService {}
