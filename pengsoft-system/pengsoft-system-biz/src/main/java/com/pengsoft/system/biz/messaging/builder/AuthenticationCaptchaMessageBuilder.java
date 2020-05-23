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

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

/**
 * Authentication captcha message builder
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Named
public class AuthenticationCaptchaMessageBuilder implements MessageBuilder {

    public static final String TEMPLATE_CODE = "authentication_captcha";

    private final MessageTemplate template;

    private final User sender;

    @Inject
    private MessageFacade facade;

    public AuthenticationCaptchaMessageBuilder(final UserFacade userFacade, final MessageTemplateFacade messageTemplateFacade) {
        sender = userFacade.findOneByUsername("admin")
                .orElseThrow(() -> new MissingConfigurationException("no user(admin) configured."));
        template = messageTemplateFacade.findOneByCode(TEMPLATE_CODE)
                .orElseThrow(() -> new MissingConfigurationException("no message template(" + TEMPLATE_CODE + ") configured."));
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
        return facade.save(message);
    }

}
