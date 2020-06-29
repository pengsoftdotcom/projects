package com.pengsoft.system.starter.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Messaging auto configure properties
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@ConfigurationProperties("pengsoft.messaging")
public class MessagingAutoConfigureProperties {

    private String mqTopic;

    private Sms sms;

}
