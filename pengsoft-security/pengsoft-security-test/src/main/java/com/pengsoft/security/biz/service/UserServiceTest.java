package com.pengsoft.security.biz.service;

import com.pengsoft.security.biz.repository.RoleRepository;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;
import java.util.List;

/**
 * {@link UserService} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles("security")
public class UserServiceTest extends BaseServiceTest<UserService> {

    @Inject
    private RoleRepository roleRepository;

    @Test
    public void init() {
        getService().resetPassword(createIfNotExists().getId(), "123123");
    }

    private User createIfNotExists() {
        final var optional = getService().findOneByUsername(Role.ADMIN);
        if (optional.isEmpty()) {
            return getService().save(new User(Role.ADMIN, "!@#123qwe"));
        } else {
            return optional.get();
        }
    }

    @Test
    public void grantRoles() {
        getService().findOneByUsername(Role.ADMIN).ifPresent(user -> roleRepository.findOneByCode(Role.ADMIN).map(List::of).ifPresent(roles -> getService().grantRoles(user, roles)));
    }

}
