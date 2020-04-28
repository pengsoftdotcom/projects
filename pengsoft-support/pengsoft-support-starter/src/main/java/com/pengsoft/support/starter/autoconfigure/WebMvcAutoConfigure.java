package com.pengsoft.support.starter.autoconfigure;

import com.pengsoft.support.commons.json.ObjectMapper;
import com.pengsoft.support.commons.util.DateUtils;
import com.pengsoft.support.starter.autoconfigure.properties.WebMvcAutoConfigureProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.inject.Inject;
import java.util.List;

/**
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Configuration
@ComponentScan({"com.*.*.commons.exception", "com.*.*.biz.api", "com.*.*.*.biz.api"})
@EnableConfigurationProperties(WebMvcAutoConfigureProperties.class)
public class WebMvcAutoConfigure implements WebMvcConfigurer {

    @Inject
    private LocalValidatorFactoryBean validator;

    @Inject
    private MessageSource messageSource;

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/static/");
    }

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper()));
    }

//    @Override
//    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(0, querydslPredicateArgumentResolver);
//    }

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        final var registrar = new DateTimeFormatterRegistrar();
        registrar.setTimeFormatter(DateUtils.timeFormatter);
        registrar.setDateFormatter(DateUtils.dateFormatter);
        registrar.setDateTimeFormatter(DateUtils.dateTimeFormatter);
        registrar.registerFormatters(registry);
    }

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*").allowCredentials(true);
    }

    @Override
    public Validator getValidator() {
        validator.setValidationMessageSource(messageSource);
        return validator;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
