package com.pengsoft.security.oauth.biz.service;

import com.pengsoft.security.oauth.biz.repository.ClientRepository;
import com.pengsoft.security.oauth.domain.entity.Client;
import com.pengsoft.support.biz.service.BeanServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The implementer of {@link ClientService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class ClientServiceImpl extends BeanServiceImpl<ClientRepository, Client, String> implements ClientService {

    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    public Client save(Client client) {
        client.setSecret(passwordEncoder.encode(client.getSecret()));
        return super.save(client);
    }

    @Override
    public Optional<Client> findOneByCode(@NotBlank String code) {
        return getRepository().findOneByCode(code);
    }

}
