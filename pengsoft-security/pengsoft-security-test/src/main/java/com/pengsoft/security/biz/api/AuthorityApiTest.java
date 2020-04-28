package com.pengsoft.security.biz.api;

import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseApiTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * {@link AuthorityApi} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles("security")
public class AuthorityApiTest extends BaseApiTest {

}
