package com.pengsoft.security.oauth.biz.facade;

import com.pengsoft.security.biz.facade.AuthorityFacade;
import com.pengsoft.security.biz.facade.RoleFacade;
import com.pengsoft.security.oauth.domain.entity.Client;
import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseFacadeTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles({ "security", "authorization-server" })
public class ClientFacadeTest extends BaseFacadeTest<ClientFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(Client.class);
        authorityFacade.saveEntityAdminAuthorities(Client.class);

        getFacade().findOneByCode("admin").ifPresent(getFacade()::delete);
        var client = new Client();
        client.setCode("admin");
        client.setName("admin");
        client.setSecret("admin");
        client.setValiditySeconds(60 * 60 * 8);
        getFacade().save(client);
    }

}
