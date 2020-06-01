package com.pengsoft.security.starter.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
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

    public String getUpdatingAuthenticationMqTopic() {
        return updatingAuthenticationMqTopic;
    }

    public void setUpdatingAuthenticationMqTopic(final String updatingAuthenticationMqTopic) {
        this.updatingAuthenticationMqTopic = updatingAuthenticationMqTopic;
    }

    public int getAllowSignInFailure() {
        return allowSignInFailure;
    }

    public void setAllowSignInFailure(final int allowSignInFailure) {
        this.allowSignInFailure = allowSignInFailure;
    }

    public List<String> getUrisPermitted() {
        return urisPermitted;
    }

    public void setUrisPermitted(final List<String> urisPermitted) {
        this.urisPermitted = urisPermitted;
    }

}
