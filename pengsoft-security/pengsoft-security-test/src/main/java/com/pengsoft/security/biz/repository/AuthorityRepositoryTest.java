package com.pengsoft.security.biz.repository;

import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseRepositoryTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * {@link AuthorityRepository} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles("security")
public class AuthorityRepositoryTest extends BaseRepositoryTest<AuthorityRepository> {

}
