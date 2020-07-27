package com.pengsoft.system.biz.messaging.builder;

import com.pengsoft.security.biz.facade.UserFacade;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.commons.exception.MissingConfigurationException;
import com.pengsoft.system.biz.facade.MessageFacade;
import com.pengsoft.system.biz.facade.MessageTemplateFacade;
import com.pengsoft.system.biz.messaging.MessageBody;
import com.pengsoft.system.domain.entity.Message;
import com.pengsoft.system.domain.entity.MessageTemplate;
import com.pengsoft.system.domain.json.CaptchaWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

/**
 * Authentication captcha message builder
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Slf4j
@Named
public class AuthenticationCaptchaMessageBuilder implements MessageBuilder {

    @Inject
    private UserFacade userFacade;

    @Inject
    private MessageTemplateFacade messageTemplateFacade;

    @Inject
    private MessageFacade messageFacade;

    private static final String TEMPLATE_CODE = "authentication_captcha";

    private MessageTemplate template;

    private User sender;

    @Override
    public void init() {
        if (sender == null) {
            sender = userFacade.findOneByUsername("admin").orElseThrow(() -> new MissingConfigurationException("no user(admin) configured."));
        }
        if (template == null) {
            template = messageTemplateFacade.findOneByCode(TEMPLATE_CODE).orElseThrow(() -> new MissingConfigurationException("no message template(" + TEMPLATE_CODE + ") configured."));
        }
    }

    @Override
    public Message build(final MessageBody body) {
        final var captcha = ((CaptchaWrapper) body.getResult()).getCaptcha();
        template.setParams(Map.of("code", captcha.getCode()));
        final var message = new Message();
        message.setTemplate(template);
        message.setSender(sender);
        message.setRecipient(captcha.getUser());
        message.setSubject(template.getSubject());
        message.setContent(template.getContent().replace("${code}", captcha.getCode()));
        message.setTypes(template.getTypes());
        return messageFacade.save(message);
    }

}
