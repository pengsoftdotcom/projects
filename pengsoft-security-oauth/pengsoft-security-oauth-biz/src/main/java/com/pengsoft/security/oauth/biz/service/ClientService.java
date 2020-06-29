package com.pengsoft.security.oauth.biz.service;

import com.pengsoft.security.oauth.domain.entity.Client;
import com.pengsoft.support.biz.service.EntityService;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The service interface of {@link Client}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface ClientService extends EntityService<Client, String> {

    /**
     * Returns an {@link Optional} of a {@link Client} with given code.
     *
     * @param code {@link Client}'s code
     */
    Optional<Client> findOneByCode(@NotBlank String code);

}
