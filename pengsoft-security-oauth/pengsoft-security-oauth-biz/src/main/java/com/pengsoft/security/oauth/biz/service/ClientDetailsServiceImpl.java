package com.pengsoft.security.oauth.biz.service;

import com.pengsoft.security.oauth.domain.DefaultClientDetails;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * The default implementer of {@link ClientDetailsService}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Inject
    private ClientService clientService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        return new DefaultClientDetails(clientService.findOneByCode(clientId)
                .orElseThrow(() -> new IllegalArgumentException("The client id is invalid: " + clientId)));
    }

}
