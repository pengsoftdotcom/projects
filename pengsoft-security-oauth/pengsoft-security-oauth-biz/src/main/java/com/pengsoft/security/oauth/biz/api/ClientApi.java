package com.pengsoft.security.oauth.biz.api;

import com.pengsoft.security.oauth.biz.facade.ClientFacade;
import com.pengsoft.security.oauth.domain.entity.Client;
import com.pengsoft.support.biz.api.BeanApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Client}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/client")
public class ClientApi extends BeanApi<ClientFacade, Client, String> {
}
