package com.pengsoft.system.starter.autoconfigure;

import com.pengsoft.system.biz.service.StorageService;
import com.pengsoft.system.biz.service.StorageServiceImpl;
import com.pengsoft.system.starter.autoconfigure.properties.StorageServiceAutoConfigureProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@EnableConfigurationProperties(StorageServiceAutoConfigureProperties.class)
public class StorageServiceAutoConfigure {

    @ConditionalOnProperty(value = "pengsoft.storage.enabled", havingValue = "true")
    @Bean
    public StorageService storageService(final StorageServiceAutoConfigureProperties properties) {
        return new StorageServiceImpl(properties.getPublicAccessPathPrefix(), properties.getLockedAccessPathPrefix(),
                properties.getPublicBucket(), properties.getLockedBucket(), properties.getEndpoint(),
                properties.getAccessKeyId(), properties.getAccessKeySecret());
    }

}
