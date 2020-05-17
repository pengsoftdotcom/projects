package com.pengsoft.security.oauth.starter.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The authorization server auto configure properties.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
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

    public String getAuthorizationServer() {
        return authorizationServer;
    }

    public void setAuthorizationServer(final String authorizationServer) {
        this.authorizationServer = authorizationServer;
    }

    public String getResourceServer() {
        return resourceServer;
    }

    public void setResourceServer(final String resourceServer) {
        this.resourceServer = resourceServer;
    }

}
