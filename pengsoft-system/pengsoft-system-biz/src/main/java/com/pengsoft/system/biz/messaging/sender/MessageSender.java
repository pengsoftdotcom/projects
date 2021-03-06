package com.pengsoft.system.biz.messaging.sender;

import com.pengsoft.system.domain.entity.Message;

/**
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface MessageSender {
    

    boolean support(Message message);

    void send(Message message) throws Exception;

}
