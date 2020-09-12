package com.pengsoft.security.biz.facade;

import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseFacadeTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;
import java.util.List;

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

        getFacade().resetPassword(createIfNotExists().getId(), "123123");
        getFacade().findOneByUsername(Role.ADMIN)
                .ifPresent(user -> roleFacade.findOneByCode(Role.ADMIN).map(List::of).ifPresent(roles -> getFacade().grantRoles(user, roles)));
    }

    private User createIfNotExists() {
        final var optional = getFacade().findOneByUsername(Role.ADMIN);
        if (optional.isEmpty()) {
            final var user = new User();
            user.setUsername(Role.ADMIN);
            user.setPassword("!@#123qwe");
            return getFacade().save(user);
        } else {
            return optional.get();
        }
    }

    @Inject
    private MessageSource messageSource;

    @Test
    public void save() {
        final var user = new User();
        user.setUsername("test");
        getFacade().save(user);
        getFacade().delete(user);
    }

}
