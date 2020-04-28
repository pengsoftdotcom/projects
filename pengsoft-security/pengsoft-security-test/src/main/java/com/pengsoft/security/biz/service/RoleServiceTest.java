package com.pengsoft.security.biz.service;

import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseServiceTest;
import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.util.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * {@link RoleService} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles("security")
public class RoleServiceTest extends BaseServiceTest<RoleService> {

    @Test
    @Order(1)
    public void init() {
        final var admin = createIfNotExists(null, Role.ADMIN, "超级管理员");
        final var securityAdmin = createIfNotExists(admin, Role.SECURITY_ADMIN, "安全模块管理员");
        createIfNotExists(securityAdmin, Role.SECURITY_ROLE_ADMIN, "安全模块角色管理员");
    }

    private Role createIfNotExists(final Role parent, final String code, final String name) {
        final var optional = getService().findOneByCode(code);
        if (optional.isEmpty()) {
            final var role = new Role();
            role.setCode(code);
            role.setName(name);
            role.setParent(parent);
            getService().save(role);
            return role;
        } else {
            return optional.get();
        }
    }

    @Test
    @Order(2)
    public void clear() {
        getService().findAll(new BooleanBuilder(), null).forEach(getService()::delete);
    }


    @Test
    public void findAllExcludeSelfAndSelfChildrenByParent() {
        final var self = getService().findOneByCode(Role.SECURITY_ROLE_ADMIN).orElse(null);
        Assert.equals(1, getService().findAllExcludeSelfAndItsChildrenByParent(self, new BooleanBuilder(), null).size());
    }

    @Test
    public void findAllExcludeSelfAndItsChildren() {
        final var self = getService().findOneByCode(Role.SECURITY_ROLE_ADMIN).orElse(null);
        Assert.equals(2, getService().findAllExcludeSelfAndItsChildren(self, new BooleanBuilder(), null).size());
    }

}
