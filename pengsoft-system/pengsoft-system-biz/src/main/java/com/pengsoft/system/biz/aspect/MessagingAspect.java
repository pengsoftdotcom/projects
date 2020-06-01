package com.pengsoft.system.biz.aspect;

import com.pengsoft.system.biz.messaging.MessageBody;
import com.pengsoft.system.biz.messaging.Messaging;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.util.SerializationUtils;

import javax.inject.Named;

/**
 * Messaging aspect.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Slf4j
@Named
@Aspect
public class MessagingAspect {

    /**
     * bean name
     */
    private static final String BN_PRODUCER = "producer";

    private MQProducer producer;

    @Value("${pengsoft.messaging.mq-topic}")
    private String topic;

    public MessagingAspect(final ApplicationContext applictionContext) {
        if (applictionContext.containsBean(BN_PRODUCER)) {
            producer = (MQProducer) applictionContext.getBean(BN_PRODUCER);
        } else {
            log.debug("message queue is disabled");
        }
    }

    @AfterReturning(pointcut = "@annotation(messaging)", returning = "result")
    public void messaging(final JoinPoint jp, final Messaging messaging, final Object result) throws Exception {
        if (producer != null) {
            final var body = new MessageBody(jp.getArgs(), result, messaging);
            producer.send(new Message(topic, SerializationUtils.serialize(body)), new SendCallback() {
                @Override
                public void onSuccess(final SendResult sendResult) {
                    log.debug("message sent successfully");
                }

                @Override
                public void onException(final Throwable e) {
                    log.error("message sent failed", e);
                }
            });
        }
    }

}
