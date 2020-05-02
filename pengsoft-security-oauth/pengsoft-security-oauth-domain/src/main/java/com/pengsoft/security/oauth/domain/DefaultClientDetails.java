package com.pengsoft.security.oauth.domain;

import com.pengsoft.security.oauth.domain.entity.Client;
import com.pengsoft.support.commons.util.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The default implementer of {@link ClientDetails}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class DefaultClientDetails implements ClientDetails {

    private static final long serialVersionUID = 9001539757906551743L;

    private final Client client;

    public DefaultClientDetails(@NotNull Client client) {
        this.client = client;
    }

    @Override
    public String getClientId() {
        return client.getCode();
    }

    @Override
    public Set<String> getResourceIds() {
        return Set.of();
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return client.getSecret();
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getScope() {
        return Set.of("all");
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return Set.of(StringUtils.split(client.getGrantTypes(), StringUtils.COMMA)).stream().map(String::trim).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return Set.of();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Set.of();
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return client.getValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Map.of();
    }

}
