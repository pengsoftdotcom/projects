package com.pengsoft.system.biz.facade;

import com.pengsoft.support.biz.facade.BeanFacade;
import com.pengsoft.system.biz.service.MessageService;
import com.pengsoft.system.domain.entity.Message;

/**
 * The facade interface of {@link Message}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface MessageFacade extends BeanFacade<MessageService, Message, String>, MessageService {

}
