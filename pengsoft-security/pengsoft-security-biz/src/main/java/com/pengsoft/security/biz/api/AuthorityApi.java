package com.pengsoft.security.biz.api;

import com.pengsoft.security.biz.facade.AuthorityFacade;
import com.pengsoft.security.domain.entity.Authority;
import com.pengsoft.support.biz.api.EntityApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Authority}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/authority")
public class AuthorityApi extends EntityApi<AuthorityFacade, Authority, String> {

}
