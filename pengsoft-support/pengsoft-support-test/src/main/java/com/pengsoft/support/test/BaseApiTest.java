package com.pengsoft.support.test;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;

/**
 * The base test superclass for api
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@AutoConfigureMockMvc
public class BaseApiTest extends BaseTest {

    @Inject
    private MockMvc mockMvc;

    public MockMvc getMockMvc() {
        return mockMvc;
    }

}
