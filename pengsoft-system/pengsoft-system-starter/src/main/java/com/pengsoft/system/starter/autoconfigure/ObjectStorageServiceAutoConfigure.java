package com.pengsoft.system.starter.autoconfigure;

import com.pengsoft.system.biz.service.ObjectStorageService;
import com.pengsoft.system.biz.service.ObjectStorageServiceImpl;
import com.pengsoft.system.starter.autoconfigure.properties.OOSAutoConfigureProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Object storage service auto configure.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(OOSAutoConfigureProperties.class)
public class ObjectStorageServiceAutoConfigure {

    @Bean
    public ObjectStorageService objectStorageService(final OOSAutoConfigureProperties properties) {
        return new ObjectStorageServiceImpl(properties.getPublicAccessPathPrefix(), properties.getLockedAccessPathPrefix(),
                properties.getPublicBucket(), properties.getLockedBucket(), properties.getEndpoint(),
                properties.getAccessKeyId(), properties.getAccessKeySecret());
    }

}
