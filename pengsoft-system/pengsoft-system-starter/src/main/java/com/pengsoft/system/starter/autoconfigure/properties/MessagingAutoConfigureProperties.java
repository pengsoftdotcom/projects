package com.pengsoft.system.starter.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Messaging auto configure properties
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@ConfigurationProperties("pengsoft.messaging")
public class MessagingAutoConfigureProperties {

    private String mqTopic;

    private Sms sms;

    public String getMqTopic() {
        return mqTopic;
    }

    public void setMqTopic(final String mqTopic) {
        this.mqTopic = mqTopic;
    }

    public Sms getSms() {
        return sms;
    }

    public void setSms(final Sms sms) {
        this.sms = sms;
    }

}
