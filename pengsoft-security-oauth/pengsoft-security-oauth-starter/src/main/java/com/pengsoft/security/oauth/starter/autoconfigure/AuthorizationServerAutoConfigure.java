package com.pengsoft.security.oauth.starter.autoconfigure;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.AccessChannel;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.RPCHook;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.util.SerializationUtils;

import com.pengsoft.security.biz.facade.UserFacade;
import com.pengsoft.security.domain.DefaultUserDetails;
import com.pengsoft.security.oauth.biz.facade.ClientFacade;
import com.pengsoft.security.oauth.commons.json.OAuth2AccessTokenMixIn;
import com.pengsoft.security.oauth.starter.autoconfigure.properties.OAuthAutoConfigureProperties;
import com.pengsoft.security.starter.autoconfigure.properties.WebSecurityAutoConfigureProperties;
import com.pengsoft.support.commons.json.ObjectMapper;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.starter.autoconfigure.properties.MessageQueueAutoConfigureProperties;
import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * Authorization server auto configure.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Slf4j
@ConditionalOnProperty(name = "pengsoft.security.oauth.authorization-server", havingValue = "enabled")
@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties({ WebSecurityAutoConfigureProperties.class, OAuthAutoConfigureProperties.class })
public class AuthorizationServerAutoConfigure extends AuthorizationServerConfigurerAdapter {

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private UserDetailsService userDetailsService;

    @Inject
    private ClientDetailsService clientDetailsService;

    @Inject
    private TokenStore tokenStore;

    @Inject
    private UserFacade userFacade;

    @Inject
    private ClientFacade clientFacade;

    @Inject
    private WebSecurityAutoConfigureProperties properties;

    public AuthorizationServerAutoConfigure(final ObjectMapper objectMapper) {
        objectMapper.addMixIn(OAuth2AccessToken.class, OAuth2AccessTokenMixIn.class);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
                .tokenStore(tokenStore)
                .userDetailsService(userDetailsService)
                .tokenGranter(getTokenGranter(endpoints));
    }

    @Bean
    public TokenStore tokenStore(final RedisConnectionFactory connectionFactory) {
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

    @Bean
    @ConditionalOnProperty(name = "pengsoft.mq.enabled", havingValue = "true")
    public MQConsumer updatingAuthenticationConsumer(final MessageQueueAutoConfigureProperties mqProperties,
            final WebSecurityAutoConfigureProperties messagingProperties) throws MQClientException {
        final var rpcHook = getRpcHook(mqProperties);
        final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(mqProperties.getGroupId(), rpcHook, new AllocateMessageQueueAveragely());
        if (rpcHook != null) {
            consumer.setAccessChannel(AccessChannel.CLOUD);
        }
        consumer.setNamesrvAddr(mqProperties.getNamesrvAddr());
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.subscribe(messagingProperties.getUpdatingAuthenticationMqTopic(), "*");
        consumer.registerMessageListener(updatingAuthenticationMessageListener());
        consumer.start();
        return consumer;
    }

    public MessageListenerConcurrently updatingAuthenticationMessageListener() {
        return (messages, context) -> {
            log.debug("updating authentication message received!");
            try {
                for (final var messageExt : messages) {
                    final var userDetails = (UserDetails) SerializationUtils.deserialize(messageExt.getBody());
                    clientFacade.findAll(new BooleanBuilder(), null).forEach(
                            client -> tokenStore.findTokensByClientIdAndUserName(client.getCode(), userDetails.getUsername()).forEach(
                                    token -> {
                                        final var authentication = tokenStore.readAuthentication(token);
                                        final var userAuthentication = new RememberMeAuthenticationToken(userDetails.getUsername(), userDetails,
                                                userDetails.getAuthorities());
                                        tokenStore.storeAccessToken(token,
                                                new OAuth2Authentication(authentication.getOAuth2Request(), userAuthentication));
                                    }));
                }
            } catch (final Exception e) {
                log.error("updating authentication message processing failed", e);
            }
            log.debug("updating authentication message processed!");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        };
    }

    private RPCHook getRpcHook(final MessageQueueAutoConfigureProperties properties) {
        if (StringUtils.isNotBlank(properties.getAccessKey()) && StringUtils.isNotBlank(properties.getSecretKey())) {
            return new AclClientRPCHook(new SessionCredentials(properties.getAccessKey(), properties.getSecretKey()));
        } else {
            return null;
        }
    }

}
