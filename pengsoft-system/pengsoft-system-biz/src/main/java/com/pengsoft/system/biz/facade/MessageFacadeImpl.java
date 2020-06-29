package com.pengsoft.system.biz.facade;

import com.pengsoft.support.biz.facade.EntityFacadeImpl;
import com.pengsoft.system.biz.service.MessageService;
import com.pengsoft.system.domain.entity.Message;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link MessageFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class MessageFacadeImpl extends EntityFacadeImpl<MessageService, Message, String> implements MessageFacade {

}
