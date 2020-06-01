package com.pengsoft.security.domain.entity;

import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseEntityTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * {@link User} bean validation test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Slf4j
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles("security")
public class UserTest extends BaseEntityTest {

    @Test
    public void validate() {
        final var user = new User();
        user.setUsername("admin");
        user.setPassword("123123");
        final var violations = getValidator().validate(user);
        violations.forEach(violation -> log.error(violation.getMessage()));
        Assertions.assertFalse(violations.isEmpty());
    }

}
