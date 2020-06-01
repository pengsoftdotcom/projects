package com.pengsoft.security.biz.aspect;

import com.pengsoft.security.commons.annotation.UpdatingAuthentication;
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
 * The updating authentication aspect, produce a MQ message to tell all consumers to do their own logic.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Slf4j
@Named
@Aspect
public class HandleUpdatingAuthenticationAspect {

    /**
     * bean name
     */
    private static final String BN_PRODUCER = "producer";

    private MQProducer producer;

    @Value("${pengsoft.security.updating-authentication-mq-topic}")
    private String topic;

    public HandleUpdatingAuthenticationAspect(final ApplicationContext applictionContext) {
        if (applictionContext.containsBean(BN_PRODUCER)) {
            producer = (MQProducer) applictionContext.getBean(BN_PRODUCER);
        } else {
            log.debug("message queue is disabled");
        }
    }

    @AfterReturning(pointcut = "@annotation(updatingAuthentication)", returning = "result")
    public void handle(final JoinPoint jp, final UpdatingAuthentication updatingAuthentication, final Object result) throws Exception {
        if (producer != null) {
            producer.send(new Message(topic, SerializationUtils.serialize(result)), new SendCallback() {
                @Override
                public void onSuccess(final SendResult sendResult) {
                    log.debug("updating authentication message sent successfully");
                }

                @Override
                public void onException(final Throwable e) {
                    log.error("updating authentication message sent failed", e);
                }
            });
        }
    }

}
