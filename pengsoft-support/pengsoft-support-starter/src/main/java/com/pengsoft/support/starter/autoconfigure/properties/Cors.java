package com.pengsoft.support.starter.autoconfigure.properties;

/**
 * The CORS auto configure properties.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class Cors {

    private boolean allowCredentials = true;

    private String allowedOrigin = "*";

    private String allowedHeader = "*";

    private String allowedMethod = "*";

    public boolean isAllowCredentials() {
        return allowCredentials;
    }

    public void setAllowCredentials(final boolean allowCredentials) {
        this.allowCredentials = allowCredentials;
    }

    public String getAllowedOrigin() {
        return allowedOrigin;
    }

    public void setAllowedOrigin(final String allowedOrigin) {
        this.allowedOrigin = allowedOrigin;
    }

    public String getAllowedHeader() {
        return allowedHeader;
    }

    public void setAllowedHeader(final String allowedHeader) {
        this.allowedHeader = allowedHeader;
    }

    public String getAllowedMethod() {
        return allowedMethod;
    }

    public void setAllowedMethod(final String allowedMethod) {
        this.allowedMethod = allowedMethod;
    }

}
