package com.pengsoft.security.biz.service;

import com.pengsoft.security.biz.repository.AuthorityRepository;
import com.pengsoft.security.domain.entity.Authority;
import com.pengsoft.support.biz.service.BeanServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The implementer of {@link AuthorityService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class AuthorityServiceImpl extends BeanServiceImpl<AuthorityRepository, Authority, String> implements AuthorityService {

    @Override
    public Authority save(final Authority authority) {
        findOneByCode(authority.getCode()).ifPresent(source -> {
            if (EntityUtils.ne(source, authority)) {
                throw exceptions.constraintViolated("code", "Exists", authority.getCode());
            }
        });
        return super.save(authority);
    }

    @Override
    public Optional<Authority> findOneByCode(@NotBlank final String code) {
        return getRepository().findOneByCode(code);
    }

}
