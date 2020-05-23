package com.pengsoft.system.biz.service;

import com.pengsoft.support.biz.service.BeanServiceImpl;
import com.pengsoft.system.biz.repository.MessageRepository;
import com.pengsoft.system.domain.entity.Message;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link MessageService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class MessageServiceImpl extends BeanServiceImpl<MessageRepository, Message, String> implements MessageService {

}
