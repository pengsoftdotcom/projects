package com.pengsoft.security.biz.service;

import com.pengsoft.security.biz.repository.AuthorityRepository;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseServiceTest;
import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;
import java.util.List;

/**
 * {@link RoleService} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles("security")
public class RoleServiceTest extends BaseServiceTest<RoleService> {

    @Inject
    private AuthorityRepository authorityRepository;

    @Test
    public void grantAuthorities() {
        authorityRepository.findOneByCode("security::role::find_one").map(List::of)
                .ifPresent(authorities -> getService().findOneByCode(Role.ADMIN).ifPresent(role -> getService().grantAuthorities(role, authorities)));
    }

    @Test
    public void find() {
        final var self = new Role();
        self.setId("d68de98b-bcbb-4283-8f05-7d16ed0e422f");
        getService().findAllExcludeSelfAndItsChildren(self, new BooleanBuilder(), null);
    }

}
