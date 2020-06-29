package com.pengsoft.system.biz.api;

import com.pengsoft.support.biz.api.EntityApi;
import com.pengsoft.system.biz.facade.MessageFacade;
import com.pengsoft.system.domain.entity.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Message}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/message")
public class MessageApi extends EntityApi<MessageFacade, Message, String> {

}
