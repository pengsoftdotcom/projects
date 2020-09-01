package com.pengsoft.security.oauth.biz.provider.token;

import com.pengsoft.support.commons.util.StringUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

/**
 * Abstract custom token granter, providing some useful methods.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
public abstract class AbstractCustomTokenGranter implements TokenGranter {

    private AuthorizationServerTokenServices tokenServices;

    private ClientDetailsService clientDetailsService;

    private OAuth2RequestFactory requestFactory;

    protected abstract String getGrantType();

    @Override
    public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
        if (StringUtils.notEquals(getGrantType(), grantType)) {
            return null;
        }
        final var clientDetails = clientDetailsService.loadClientByClientId(tokenRequest.getClientId());
        final var grantTypes = clientDetails.getAuthorizedGrantTypes();
        if (!CollectionUtils.containsAny(grantTypes, grantType)) {
            throw new InvalidClientException("Unauthorized grant type: " + grantType);
        }
        return getAccessToken(clientDetails, tokenRequest);
    }

    protected abstract OAuth2AccessToken getAccessToken(ClientDetails clientDetails, TokenRequest tokenRequest);

}
