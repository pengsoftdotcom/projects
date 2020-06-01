package com.pengsoft.system.biz.messaging.sender;

import com.pengsoft.support.commons.json.ObjectMapper;
import com.pengsoft.system.domain.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE)
@Named
public class LogMessageSender implements MessageSender {

    @Inject
    private ObjectMapper objectMapper;

    @Override
    public boolean support(final Message message) {
        return true;
    }

    @Override
    public void send(final Message message) throws Exception {
        log.debug("message sent: " + objectMapper.writeValueAsString(message));
    }

}
