package com.pengsoft.security.oauth.biz.facade;

import com.pengsoft.security.oauth.biz.service.ClientService;
import com.pengsoft.security.oauth.domain.entity.Client;
import com.pengsoft.support.biz.facade.EntityFacade;

/**
 * The facade interface of {@link Client}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface ClientFacade extends EntityFacade<ClientService, Client, String>, ClientService {

}
