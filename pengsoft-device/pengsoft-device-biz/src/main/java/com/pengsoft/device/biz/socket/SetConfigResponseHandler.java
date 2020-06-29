package com.pengsoft.device.biz.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pengsoft.support.commons.json.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Slf4j
@Named
public class SetConfigResponseHandler implements ResponseHandler {

    @Inject
    private ObjectMapper objectMapper;

    @Override
    public String getType() {
        return "set_config_rsp";
    }

    @Override
    public void handle(final Response response) {
        try {
            log.debug("receive response from device:\n{}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
        } catch (final JsonProcessingException e) {
            log.error("write json error", e);
        }
    }

}
