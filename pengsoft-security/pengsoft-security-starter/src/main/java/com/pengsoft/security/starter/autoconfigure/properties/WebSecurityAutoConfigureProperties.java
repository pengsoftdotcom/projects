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
     * The URIs permitted.
     */
    private List<String> urisPermitted = List.of();

    public List<String> getUrisPermitted() {
        return urisPermitted;
    }

    public void setUrisPermitted(final List<String> urisPermitted) {
        this.urisPermitted = urisPermitted;
    }
}
