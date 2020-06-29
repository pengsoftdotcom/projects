package com.pengsoft.system.biz.api;

import com.pengsoft.support.biz.api.EntityApi;
import com.pengsoft.system.biz.facade.MessageTemplateFacade;
import com.pengsoft.system.domain.entity.MessageTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link MessageTemplate}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/message-template")
public class MessageTemplateApi extends EntityApi<MessageTemplateFacade, MessageTemplate, String> {

}
