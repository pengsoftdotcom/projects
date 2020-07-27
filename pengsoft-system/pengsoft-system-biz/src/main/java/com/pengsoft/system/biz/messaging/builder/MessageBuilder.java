package com.pengsoft.system.biz.messaging.builder;

import com.pengsoft.system.biz.messaging.MessageBody;
import com.pengsoft.system.domain.entity.Message;

/**
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface MessageBuilder {

    /**
     * Init builder.
     */
    default void init() {
    }

    /**
     * Returns a {@link Message} from a {@link MessageBody}
     */
    Message build(MessageBody body);

}
