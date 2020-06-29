package com.pengsoft.security.starter.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@ConfigurationProperties("pengsoft.security")
public class WebSecurityAutoConfigureProperties {

    /**
     * The updating authentication MQ topic
     */
    private String updatingAuthenticationMqTopic;

    /**
     * The maximum count of sign in failure
     */
    private int allowSignInFailure = 5;

    /**
     * The URIs permitted.
     */
    private List<String> urisPermitted = List.of();

}
