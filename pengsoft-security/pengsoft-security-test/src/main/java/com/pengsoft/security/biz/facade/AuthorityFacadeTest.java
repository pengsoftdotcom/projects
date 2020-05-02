package com.pengsoft.security.biz.facade;

import com.pengsoft.security.domain.entity.Authority;
import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseFacadeTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * {@link AuthorityFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles("security")
public class AuthorityFacadeTest extends BaseFacadeTest<AuthorityFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(Authority.class);
        getFacade().saveEntityAdminAuthorities(Authority.class);
    }

}
