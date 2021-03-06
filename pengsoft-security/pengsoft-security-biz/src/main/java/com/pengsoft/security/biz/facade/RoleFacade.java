package com.pengsoft.security.biz.facade;

import com.pengsoft.security.biz.service.RoleService;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.support.biz.facade.TreeEntityFacade;

/**
 * The facade interface of {@link Role}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface RoleFacade extends TreeEntityFacade<RoleService, Role, String>, RoleService {


}
