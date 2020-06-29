package com.pengsoft.security.biz.facade;

import com.pengsoft.security.biz.service.RoleService;
import com.pengsoft.security.domain.entity.Authority;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.support.biz.facade.TreeEntityFacadeImpl;
import com.pengsoft.support.domain.entity.Entity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * The implementer of {@link RoleFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class RoleFacadeImpl extends TreeEntityFacadeImpl<RoleService, Role, String> implements RoleFacade {

    @Override
    public Role saveEntityAdmin(Class<? extends Entity<? extends Serializable>> entityClass) {
        return getService().saveEntityAdmin(entityClass);
    }

    @Override
    public void grantAuthorities(final Role role, final List<Authority> authorities) {
        getService().grantAuthorities(role, authorities);
    }

    @Override
    public Optional<Role> findOneByCode(@NotBlank final String code) {
        return getService().findOneByCode(code);
    }

}
