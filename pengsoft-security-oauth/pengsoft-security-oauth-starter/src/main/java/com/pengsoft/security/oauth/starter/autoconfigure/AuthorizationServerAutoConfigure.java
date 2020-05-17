package com.pengsoft.security.oauth.starter.autoconfigure;

import com.pengsoft.security.biz.facade.UserFacade;
import com.pengsoft.security.domain.DefaultUserDetails;
import com.pengsoft.security.oauth.commons.json.OAuth2AccessTokenMixIn;
import com.pengsoft.security.oauth.starter.autoconfigure.properties.OAuthAutoConfigureProperties;
import com.pengsoft.security.starter.autoconfigure.properties.WebSecurityAutoConfigureProperties;
import com.pengsoft.support.commons.json.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Authorization server auto configure.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@ConditionalOnProperty(name = "pengsoft.security.oauth.authorization-server", havingValue = "enabled")
@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(OAuthAutoConfigureProperties.class)
public class AuthorizationServerAutoConfigure extends AuthorizationServerConfigurerAdapter {

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private UserDetailsService userDetailsService;

    @Inject
    private ClientDetailsService clientDetailsService;

    @Inject
    private RedisConnectionFactory connectionFactory;

    @Inject
    private UserFacade userFacade;

    @Inject
    private WebSecurityAutoConfigureProperties properties;

    public AuthorizationServerAutoConfigure(final ObjectMapper objectMapper) {
        objectMapper.addMixIn(OAuth2AccessToken.class, OAuth2AccessTokenMixIn.class);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
                .tokenStore(tokenStore())
                .userDetailsService(userDetailsService)
                .tokenGranter(getTokenGranter(endpoints));
    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(connectionFactory);
    }

    private TokenGranter getTokenGranter(final AuthorizationServerEndpointsConfigurer endpoints) {
        final var tokenServices = endpoints.getTokenServices();
        final var authorizationCodeServices = endpoints.getAuthorizationCodeServices();
        final var requestFactory = new DefaultOAuth2RequestFactory(clientDetailsService);
        final var tokenGranters = new ArrayList<TokenGranter>();
        tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetailsService, requestFactory));
        tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetailsService, requestFactory));
        tokenGranters.add(new ImplicitTokenGranter(tokenServices, clientDetailsService, requestFactory));
        tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices, clientDetailsService, requestFactory));
        tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices, clientDetailsService, requestFactory));

        return new CompositeTokenGranter(tokenGranters);
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @EventListener
    public void authenticationSuccessEventListener(final AuthenticationSuccessEvent event) {
        final var authentication = event.getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken && authentication.getPrincipal() instanceof DefaultUserDetails) {
            userFacade.signInSuccess(authentication.getName());
        }
    }

    @EventListener
    public void authenticationFailedEventListener(final AbstractAuthenticationFailureEvent event) {
        final var exception = event.getException();
        final var authentication = event.getAuthentication();
        if (exception instanceof BadCredentialsException) {
            userFacade.signInFailure(authentication.getName(), properties.getAllowSignInFailure());
        }
    }

}
