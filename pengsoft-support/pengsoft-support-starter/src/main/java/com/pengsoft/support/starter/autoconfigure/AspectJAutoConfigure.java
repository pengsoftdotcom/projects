package com.pengsoft.support.starter.autoconfigure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Aspect auto configure.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.*.*.biz.aspect", "com.*.*.*.biz.aspect"})
public class AspectJAutoConfigure {

}
