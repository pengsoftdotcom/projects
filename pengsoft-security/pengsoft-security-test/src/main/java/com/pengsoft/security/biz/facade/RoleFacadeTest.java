package com.pengsoft.security.biz.facade;

import com.pengsoft.security.domain.entity.QRole;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseFacadeTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * {@link RoleFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles("security")
public class RoleFacadeTest extends BaseFacadeTest<RoleFacade> {

    @Inject
    private AuthorityFacade authorityFacade;

    @Test
    public void init() {
        getFacade().saveEntityAdmin(Role.class);
        authorityFacade.saveEntityAdminAuthorities(Role.class);
    }

    @Test
    public void findOneByCode() {
        getFacade().findOneByCode("admin").ifPresent(role -> System.out.println(getFacade().count(QRole.role.parent.eq(role))));
    }

}
