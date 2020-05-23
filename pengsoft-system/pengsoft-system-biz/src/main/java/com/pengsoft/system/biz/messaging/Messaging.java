package com.pengsoft.system.biz.messaging;

import com.pengsoft.system.biz.messaging.builder.MessageBuilder;
import com.pengsoft.system.domain.entity.MessageType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Any spring bean method with this annotation will send a message after returning
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Messaging {

    /**
     * {@link MessageBuilder} spring bean name.
     */
    String value();

    MessageType[] types() default { MessageType.SMS };

}
