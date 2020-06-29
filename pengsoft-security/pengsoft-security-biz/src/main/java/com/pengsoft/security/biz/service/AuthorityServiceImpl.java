package com.pengsoft.security.biz.service;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.pengsoft.security.biz.repository.AuthorityRepository;
import com.pengsoft.security.domain.entity.Authority;
import com.pengsoft.support.biz.service.EntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;

/**
 * The implementer of {@link AuthorityService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class AuthorityServiceImpl extends EntityServiceImpl<AuthorityRepository, Authority, String> implements AuthorityService {

    @Override
    public Authority save(final Authority authority) {
        findOneByCode(authority.getCode()).ifPresent(source -> {
            if (EntityUtils.ne(source, authority)) {
                throw getExceptions().constraintViolated("code", "Exists", authority.getCode());
            }
        });
        return super.save(authority);
    }

    @Override
    public Optional<Authority> findOneByCode(@NotBlank final String code) {
        return getRepository().findOneByCode(code);
    }

}
