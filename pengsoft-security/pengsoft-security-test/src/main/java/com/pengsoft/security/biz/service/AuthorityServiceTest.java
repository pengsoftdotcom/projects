package com.pengsoft.security.biz.service;

import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseServiceTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * {@link AuthorityService} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles("security")
public class AuthorityServiceTest extends BaseServiceTest<AuthorityService> {

}
