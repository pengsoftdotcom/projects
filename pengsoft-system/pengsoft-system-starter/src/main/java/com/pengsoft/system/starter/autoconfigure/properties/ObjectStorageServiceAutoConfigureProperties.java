package com.pengsoft.system.starter.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Object storage service auto configure properties.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@ConfigurationProperties("pengsoft.oss")
public class ObjectStorageServiceAutoConfigureProperties {

    private boolean enabled;

    private String accessKeyId;

    private String accessKeySecret;

    private String publicBucket;

    private String lockedBucket;

    private String endpoint;

    private String publicAccessPathPrefix;

    private String lockedAccessPathPrefix;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
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

    public String getPublicBucket() {
        return publicBucket;
    }

    public void setPublicBucket(final String publicBucket) {
        this.publicBucket = publicBucket;
    }

    public String getLockedBucket() {
        return lockedBucket;
    }

    public void setLockedBucket(final String lockedBucket) {
        this.lockedBucket = lockedBucket;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(final String endpoint) {
        this.endpoint = endpoint;
    }

    public String getPublicAccessPathPrefix() {
        return publicAccessPathPrefix;
    }

    public void setPublicAccessPathPrefix(final String publicAccessPathPrefix) {
        this.publicAccessPathPrefix = publicAccessPathPrefix;
    }

    public String getLockedAccessPathPrefix() {
        return lockedAccessPathPrefix;
    }

    public void setLockedAccessPathPrefix(final String lockedAccessPathPrefix) {
        this.lockedAccessPathPrefix = lockedAccessPathPrefix;
    }

}
