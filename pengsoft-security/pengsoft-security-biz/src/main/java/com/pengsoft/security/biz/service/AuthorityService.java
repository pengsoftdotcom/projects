package com.pengsoft.security.biz.service;

import com.pengsoft.security.domain.entity.Authority;
import com.pengsoft.support.biz.service.EntityService;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The service interface of {@link Authority}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface AuthorityService extends EntityService<Authority, String> {

    /**
     * Returns an {@link Optional} of a {@link Authority} with given code.
     *
     * @param code {@link Authority}'s code
     */
    Optional<Authority> findOneByCode(@NotBlank String code);

}
