package com.pengsoft.security.biz.service;

import com.pengsoft.security.biz.repository.RoleRepository;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
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
    public void grantRoles() {
        getService().findOneByUsername(Role.ADMIN)
                .ifPresent(user -> roleRepository.findOneByCode(Role.ADMIN).map(List::of).ifPresent(roles -> getService().grantRoles(user, roles)));
    }

    @Test
    @WithUserDetails("admin")
    public void resetPassword() {
        getService().resetPassword(SecurityUtils.getUserId(), "123123");
    }

}
