package com.pengsoft.security.biz.facade;

import com.pengsoft.security.domain.entity.User;
import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseFacadeTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * {@link UserFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles("security")
public class UserFacadeTest extends BaseFacadeTest<UserFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(User.class);
        authorityFacade.saveEntityAdminAuthorities(User.class);
    }

}
