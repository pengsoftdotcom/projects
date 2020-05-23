package com.pengsoft.support.starter.autoconfigure;

import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.starter.autoconfigure.properties.MessageQueueAutoConfigureProperties;
import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.AccessChannel;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.remoting.RPCHook;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Message queue auto configure.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(MessageQueueAutoConfigureProperties.class)
public class MessageQueueAutoConfigure {

    @Bean
    @ConditionalOnProperty(name = "pengsoft.mq.enabled", havingValue = "true")
    public MQProducer producer(final MessageQueueAutoConfigureProperties properties) throws MQClientException {
        final var rpcHook = getRpcHook(properties);
        final var producer = new TransactionMQProducer(properties.getGroupId(), rpcHook);
        if (rpcHook != null) {
            producer.setAccessChannel(AccessChannel.CLOUD);
        }
        producer.setNamesrvAddr(properties.getNamesrvAddr());
        producer.start();
        return producer;
    }

    private RPCHook getRpcHook(final MessageQueueAutoConfigureProperties properties) {
        if (StringUtils.isNotBlank(properties.getAccessKey()) && StringUtils.isNotBlank(properties.getSecretKey())) {
            return new AclClientRPCHook(new SessionCredentials(properties.getAccessKey(), properties.getSecretKey()));
        } else {
            return null;
        }
    }

}
