package com.pengsoft.security.biz.facade;

import com.pengsoft.security.biz.service.AuthorityService;
import com.pengsoft.security.domain.entity.Authority;
import com.pengsoft.support.biz.facade.BeanFacadeImpl;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The implementer of {@link AuthorityFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class AuthorityFacadeImpl extends BeanFacadeImpl<AuthorityService, Authority, String> implements AuthorityFacade {

    @Override
    public Optional<Authority> findOneByCode(@NotBlank final String code) {
        return getService().findOneByCode(code);
    }

}
