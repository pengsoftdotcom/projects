package com.pengsoft.system.biz.facade;

import com.pengsoft.security.biz.facade.AuthorityFacade;
import com.pengsoft.security.biz.facade.RoleFacade;
import com.pengsoft.support.test.BaseFacadeTest;
import com.pengsoft.system.domain.entity.DictionaryType;
import com.pengsoft.system.starter.SystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * {@link DictionaryTypeFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SystemApplication.class)
@ActiveProfiles({ "security", "system" })
public class DictionaryTypeFacadeTest extends BaseFacadeTest<DictionaryTypeFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(DictionaryType.class);
        authorityFacade.saveEntityAdminAuthorities(DictionaryType.class);
    }

}
