package com.pengsoft.security.oauth.starter.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The authorization server auto configure properties.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@ConfigurationProperties("pengsoft.security.oauth.authorization-server")
public class AuthorizationServerAutoConfigureProperties {

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
