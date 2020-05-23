package com.pengsoft.system.biz.facade;

import com.pengsoft.support.biz.facade.BeanFacade;
import com.pengsoft.system.biz.service.MessageTemplateService;
import com.pengsoft.system.domain.entity.MessageTemplate;

/**
 * The facade interface of {@link MessageTemplate}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface MessageTemplateFacade extends BeanFacade<MessageTemplateService, MessageTemplate, String>, MessageTemplateService {

}
