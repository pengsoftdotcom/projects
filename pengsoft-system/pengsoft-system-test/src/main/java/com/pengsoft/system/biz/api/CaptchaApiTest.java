package com.pengsoft.system.biz.api;

import com.pengsoft.support.test.BaseApiTest;
import com.pengsoft.system.starter.SystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SystemApplication.class)
@ActiveProfiles({ "security", "system" })
public class CaptchaApiTest extends BaseApiTest {

    @Test
    public void generate() throws Exception {
        final var content = "mobile=18508101366&type=authentication";
        getMockMvc().perform(MockMvcRequestBuilders
                .post("/api/captcha/generate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(content)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

}
