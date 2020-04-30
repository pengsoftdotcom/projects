package com.pengsoft.security.biz.api;

import com.pengsoft.security.domain.entity.User;
import com.pengsoft.security.starter.SecurityApplication;
import com.pengsoft.support.test.BaseApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link UserApi} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles("security")
public class UserApiTest extends BaseApiTest {

    public static final String PASSWORD = "123123";

    @Test
    public void save() throws Exception {
        final var user = new User();
        user.setUsername("admin");
        user.setPassword("dp@65198404");
        getMockMvc().perform(MockMvcRequestBuilders
                .post("/api/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getObjectMapper().writeValueAsString(user))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void changePassword() throws Exception {
        final var content = Map.of("oldPassword", PASSWORD, "newPassword", PASSWORD).entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        getMockMvc().perform(MockMvcRequestBuilders
                .post("/api/user/change-password")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(content)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void resetPassword() throws Exception {
        final var content = Map.of("id", "45d11b4d-49ad-49fd-bf7c-6e79fcdafe41", "password", PASSWORD).entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        getMockMvc().perform(MockMvcRequestBuilders
                .post("/api/user/reset-password")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(content)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findAllUserRolesByUser() throws Exception {
        final var content = Map.of("id", "6c0bfff5-5c48-4aec-b224-1e9d97a1692a").entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        getMockMvc().perform(MockMvcRequestBuilders
                .get("/api/user/find-all-user-roles-by-user")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(content)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

}
