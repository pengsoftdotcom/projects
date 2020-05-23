package com.pengsoft.system.starter.autoconfigure.properties;

/**
 * Aliyun SMS auto configure.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class Sms {

    private boolean enabled;

    private String regionId;

    private String accessKeyId;

    private String accessKeySecret;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(final String regionId) {
        this.regionId = regionId;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(final String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(final String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

}
