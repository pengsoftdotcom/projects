package com.pengsoft.security.oauth.starter.autoconfigure;

import com.pengsoft.security.oauth.starter.autoconfigure.properties.OAuthAutoConfigureProperties;
import com.pengsoft.security.starter.autoconfigure.properties.WebSecurityAutoConfigureProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.inject.Inject;

/**
 * Resource server auto configure.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@ConditionalOnProperty(name = "pengsoft.security.oauth.resource-server", havingValue = "enabled")
@Configuration
@EnableResourceServer
@EnableConfigurationProperties(OAuthAutoConfigureProperties.class)
public class ResourceServerAutoConfigure extends ResourceServerConfigurerAdapter {

    @Inject
    private WebSecurityAutoConfigureProperties properties;

    @Inject
    private ClientDetailsService clientDetailsService;

    @Inject
    private RedisConnectionFactory connectionFactory;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(properties.getUrisPermitted().toArray(String[]::new)).permitAll()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
        final var tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setTokenStore(new RedisTokenStore(connectionFactory));
        resources.tokenServices(tokenServices);
    }

}
