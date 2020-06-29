package com.pengsoft.security.oauth.starter.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The authorization server auto configure properties.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@ConfigurationProperties("pengsoft.security.oauth")
public class OAuthAutoConfigureProperties {

    /**
     * Optional values are 'enabled' and 'disabled'.
     */
    private String authorizationServer;

    /**
     * Optional values are 'enabled' and 'disabled'.
     */
    private String resourceServer;

}
