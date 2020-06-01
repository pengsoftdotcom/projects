package com.pengsoft.security.biz.service;

import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * {@link DefaultUserDetailsService} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles("security")
public class DefaultUserDetailsServiceTest extends BaseTest {

    @Inject
    private DefaultUserDetailsService service;

    @Test
    public void loadUserByUsername() {
        service.loadUserByUsername(Role.ADMIN);
    }


}
