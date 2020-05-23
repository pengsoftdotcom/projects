package com.pengsoft.system.starter.autoconfigure;

import com.pengsoft.system.biz.service.ObjectStorageService;
import com.pengsoft.system.biz.service.ObjectStorageServiceImpl;
import com.pengsoft.system.starter.autoconfigure.properties.ObjectStorageServiceAutoConfigureProperties;
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
@EnableConfigurationProperties(ObjectStorageServiceAutoConfigureProperties.class)
public class ObjectStorageServiceAutoConfigure {

    @ConditionalOnProperty(value = "pengsoft.oss.enabled", havingValue = "true")
    @Bean
    public ObjectStorageService objectStorageService(final ObjectStorageServiceAutoConfigureProperties properties) {
        return new ObjectStorageServiceImpl(properties.getPublicAccessPathPrefix(), properties.getLockedAccessPathPrefix(),
                properties.getPublicBucket(), properties.getLockedBucket(), properties.getEndpoint(),
                properties.getAccessKeyId(), properties.getAccessKeySecret());
    }

}
