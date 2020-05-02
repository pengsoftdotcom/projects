package com.pengsoft.support.starter.autoconfigure;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * JPA auto configure.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.*.*.biz.facade", "com.*.*.*.biz.facade" })
@ComponentScan({ "com.*.*.biz.service", "com.*.*.*.biz.service" })
@EnableJpaRepositories({ "com.*.*.biz.repository", "com.*.*.*.biz.repository" })
@EntityScan({ "com.*.*.domain.entity", "com.*.*.*.domain.entity" })
public class JpaAutoConfigure {

}
