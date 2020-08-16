package com.pengsoft.system.starter.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Object storage service auto configure properties.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@ConfigurationProperties("pengsoft.storage")
public class StorageServiceAutoConfigureProperties {

    private boolean enabled;

    private String accessKeyId;

    private String accessKeySecret;

    private String publicBucket;

    private String lockedBucket;

    private String endpoint;

    private String publicAccessPathPrefix;

    private String lockedAccessPathPrefix;

}
