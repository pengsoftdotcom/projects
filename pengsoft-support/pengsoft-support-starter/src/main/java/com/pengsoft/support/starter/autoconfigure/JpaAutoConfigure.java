package com.pengsoft.support.starter.autoconfigure;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * JPA auto configure.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Configuration
@ComponentScan({ "com.*.*.biz.facade", "com.*.*.*.biz.facade" })
@ComponentScan({ "com.*.*.biz.service", "com.*.*.*.biz.service" })
@EnableSpringDataWebSupport
@EnableJpaRepositories({ "com.*.*.biz.repository", "com.*.*.*.biz.repository" })
@EntityScan({ "com.*.*.domain.entity", "com.*.*.*.domain.entity" })
public class JpaAutoConfigure {

}
