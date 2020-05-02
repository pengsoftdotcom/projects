package com.pengsoft.security.oauth.biz.repository;

import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * {@link ClientRepository} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles({ "security", "authorization-server" })
public class ClientRepositoryTest extends BaseRepositoryTest<ClientRepository> {

    @Test
    public void findOneByCode() {
        getRepository().findOneByCode("admin");
    }

}
