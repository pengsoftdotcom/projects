package com.pengsoft.security.oauth.biz.facade;

import com.pengsoft.security.oauth.biz.service.ClientService;
import com.pengsoft.security.oauth.domain.entity.Client;
import com.pengsoft.support.biz.facade.BeanFacadeImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link ClientFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class ClientFacadeImpl extends BeanFacadeImpl<ClientService, Client, String> implements ClientFacade {

    @Override
    public Optional<Client> findOneByCode(String code) {
        return getService().findOneByCode(code);
    }
}
