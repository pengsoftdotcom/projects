package com.pengsoft.system.starter.autoconfigure;

import java.util.List;

import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.AccessChannel;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.RPCHook;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.pengsoft.support.commons.json.ObjectMapper;
import com.pengsoft.support.commons.util.DateUtils;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.starter.autoconfigure.properties.MessageQueueAutoConfigureProperties;
import com.pengsoft.system.biz.facade.MessageFacade;
import com.pengsoft.system.biz.messaging.MessageBody;
import com.pengsoft.system.biz.messaging.builder.MessageBuilder;
import com.pengsoft.system.biz.messaging.sender.MessageSender;
import com.pengsoft.system.biz.messaging.sender.SmsMessageSender;
import com.pengsoft.system.starter.autoconfigure.properties.MessagingAutoConfigureProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * Messaging auto configure
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Slf4j
@Configuration
@ComponentScan("com.*.system.biz.messaging")
@EnableConfigurationProperties({ MessageQueueAutoConfigureProperties.class, MessagingAutoConfigureProperties.class })
public class MessagingAutoConfigure {

    @Bean
    @ConditionalOnProperty(name = "pengsoft.mq.enabled", havingValue = "true")
    public MQConsumer messagingConsumer(final MessageQueueAutoConfigureProperties mqProperties,
            final MessagingAutoConfigureProperties messagingProperties, final ApplicationContext applicationContext,
            final List<MessageSender> messageSenders, final MessageFacade messageFacade) throws MQClientException {
        final var rpcHook = getRpcHook(mqProperties);
        final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(mqProperties.getGroupId(), rpcHook, new AllocateMessageQueueAveragely());
        if (rpcHook != null) {
            consumer.setAccessChannel(AccessChannel.CLOUD);
        }
        consumer.setNamesrvAddr(mqProperties.getNamesrvAddr());
        consumer.subscribe(messagingProperties.getMqTopic(), "*");
        consumer.registerMessageListener(messagingMessageListener(applicationContext, messageSenders, messageFacade));
        consumer.start();
        return consumer;
    }

    private RPCHook getRpcHook(final MessageQueueAutoConfigureProperties properties) {
        if (StringUtils.isNotBlank(properties.getAccessKey()) && StringUtils.isNotBlank(properties.getSecretKey())) {
            return new AclClientRPCHook(new SessionCredentials(properties.getAccessKey(), properties.getSecretKey()));
        } else {
            return null;
        }
    }

    public MessageListenerConcurrently messagingMessageListener(final ApplicationContext applicationContext, final List<MessageSender> messageSenders,
            final MessageFacade messageFacade) {
        return (messages, context) -> {
            log.debug("messaging message received!");
            try {
                for (final var messageExt : messages) {
                    final var messageBody = (MessageBody) SerializationUtils.deserialize(messageExt.getBody());
                    final var messaging = messageBody.getMessaging();
                    final var messageBuilder = applicationContext.getBean(messaging.value(), MessageBuilder.class);
                    final var message = messageBuilder.build(messageBody);
                    messageFacade.save(message);

                    for (final var messageSender : messageSenders) {
                        messageSender.send(message);
                        message.setSentAt(DateUtils.currentDateTime());
                        messageFacade.save(message);
                    }
                }
            } catch (final Exception e) {
                log.error("messaging message processing failed", e);
            }
            log.debug("messaging message processed!");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        };
    }

    @Bean
    @ConditionalOnProperty(prefix = "pengsoft.messaging.sms", name = "enabled", havingValue = "true")
    public SmsMessageSender smsMessageSender(final MessagingAutoConfigureProperties properties, final ObjectMapper objectMapper) {
        final var sms = properties.getSms();
        final var client = new DefaultAcsClient(DefaultProfile.getProfile(sms.getRegionId(), sms.getAccessKeyId(), sms.getAccessKeySecret()));
        return new SmsMessageSender(client, objectMapper);
    }

}
