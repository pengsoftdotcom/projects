package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.basedata.starter.BasedataApplication;
import com.pengsoft.security.biz.facade.AuthorityFacade;
import com.pengsoft.security.biz.facade.RoleFacade;
import com.pengsoft.security.biz.facade.UserFacade;
import com.pengsoft.support.test.BaseFacadeTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * {@link PersonFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = BasedataApplication.class)
@ActiveProfiles({ "security", "system", "basedata" })
public class PersonFacadeTest extends BaseFacadeTest<PersonFacade> {

    @Inject
    private UserFacade userFacade;

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(Person.class);
        authorityFacade.saveEntityAdminAuthorities(Person.class);
    }

}
