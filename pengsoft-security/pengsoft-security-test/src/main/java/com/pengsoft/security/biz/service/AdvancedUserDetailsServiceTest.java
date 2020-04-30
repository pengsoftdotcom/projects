package com.pengsoft.security.biz.service;

import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * {@link AdvancedUserDetailsService} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles("security")
public class AdvancedUserDetailsServiceTest extends BaseTest {

    @Inject
    private AdvancedUserDetailsService service;

    @Test
    public void loadUserByUsername() {
        service.loadUserByUsername(Role.ADMIN);
    }


}
