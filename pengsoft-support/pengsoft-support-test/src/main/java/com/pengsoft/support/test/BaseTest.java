package com.pengsoft.support.test;

import javax.inject.Inject;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;

import com.pengsoft.support.commons.json.ObjectMapper;

/**
 * The base test superclass.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@TestMethodOrder(OrderAnnotation.class)
public class BaseTest {

    @Inject
    private ObjectMapper objectMapper;

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

}
