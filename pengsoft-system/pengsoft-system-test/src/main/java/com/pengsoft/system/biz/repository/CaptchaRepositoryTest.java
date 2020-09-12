package com.pengsoft.system.biz.repository;

import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.commons.util.DateUtils;
import com.pengsoft.support.test.BaseRepositoryTest;
import com.pengsoft.system.starter.SystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;

/**
 * {@link CaptchaRepository} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SystemApplication.class)
@ActiveProfiles({ "security", "system" })
public class CaptchaRepositoryTest extends BaseRepositoryTest<CaptchaRepository> {

    @Test
    @WithUserDetails("18508101366")
    public void findAllByUserAndTypeAndCreatedAtAfterOrderByCreatedAtDesc() {
        getRepository().findAllByUserAndCreatedAtAfterOrderByCreatedAtDesc(SecurityUtils.getUser(), DateUtils.currentDate().atStartOfDay())
                .forEach(captcha -> System.out.println(captcha.getCode()));

    }

}
