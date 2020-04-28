package com.pengsoft.security.biz.facade;

import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseFacadeTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * {@link AuthorityFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles("security")
public class AuthorityFacadeTest extends BaseFacadeTest<AuthorityFacade> {

}
