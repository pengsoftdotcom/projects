package com.pengsoft.system.biz.api;

import com.pengsoft.support.test.BaseApiTest;
import com.pengsoft.system.starter.SystemApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;

/**
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Slf4j
@SpringBootTest(classes = SystemApplication.class)
@ActiveProfiles({ "security", "system" })
public class RegionApiTest extends BaseApiTest {

    @Test
    @WithUserDetails("admin")
    public void generateForAuthentication() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders
                .get("/api/region/find-all-indexed-cities")
        ).andDo(handler -> {
            System.out.println(handler.getResponse().getContentAsString(Charset.forName("UTF-8")));
        }).andExpect(MockMvcResultMatchers.status().isOk());
    }

}
