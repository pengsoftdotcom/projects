package com.pengsoft.support.starter.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Message queue auto configure properties
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@ConfigurationProperties("pengsoft.mq")
public class MessageQueueAutoConfigureProperties {

    private boolean enabled;

    private String groupId = "pengsoft";

    private String topic = "pengsoft.default";

    private String accessKey;

    private String secretKey;

    private String namesrvAddr = "rocketmq-server:9876";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(final String groupId) {
        this.groupId = groupId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(final String topic) {
        this.topic = topic;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(final String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(final String secretKey) {
        this.secretKey = secretKey;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(final String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

}
