package com.pengsoft.system.biz.provider.token;

import com.pengsoft.security.biz.service.DefaultUserDetailsService;
import com.pengsoft.security.domain.DefaultUserDetails;
import com.pengsoft.security.oauth.biz.provider.token.AbstractCustomTokenGranter;
import com.pengsoft.support.commons.exception.BusinessException;
import com.pengsoft.system.biz.service.CaptchaService;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * If the giving captcha is valid, and the access token will be granted.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Named
public class CaptchaTokenGranter extends AbstractCustomTokenGranter {

    private static final String EC_CAPTCHA_INVALID = "captcha.invalid";

    private static final String PARAM_CAPTCHA = "captcha";

    private static final String PARAM_USERNAME = "username";

    @Inject
    private UserDetailsService userDetailsService;

    @Inject
    private CaptchaService captchaService;

    @Override
    public String getGrantType() {
        return "captcha";
    }

    @Override
    protected OAuth2AccessToken getAccessToken(ClientDetails clientDetails, TokenRequest tokenRequest) {
        final var oauth2Request = getRequestFactory().createOAuth2Request(clientDetails, tokenRequest);
        final var userDetails = (DefaultUserDetails) userDetailsService.loadUserByUsername(oauth2Request.getRequestParameters().get(PARAM_USERNAME));
        if (!captchaService.isValid(userDetails.getUser(), oauth2Request.getRequestParameters().get(PARAM_CAPTCHA))) {
            throw new BusinessException(EC_CAPTCHA_INVALID);
        }
        final var authentication = new RememberMeAuthenticationToken(userDetails.getUsername(), userDetails, userDetails.getAuthorities());
        return getTokenServices().createAccessToken(new OAuth2Authentication(oauth2Request, authentication));
    }

}
