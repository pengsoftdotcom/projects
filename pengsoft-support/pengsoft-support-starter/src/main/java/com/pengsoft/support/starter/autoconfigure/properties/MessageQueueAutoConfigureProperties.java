package com.pengsoft.support.starter.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Message queue auto configure properties
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@ConfigurationProperties("pengsoft.mq")
public class MessageQueueAutoConfigureProperties {

    private boolean enabled;

    private String groupId = "pengsoft";

    private String topic = "pengsoft.default";

    private String accessKey;

    private String secretKey;

    private String namesrvAddr = "localhost:9876";

}
