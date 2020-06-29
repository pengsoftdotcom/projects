package com.pengsoft.system.biz.facade;

import com.pengsoft.support.biz.facade.EntityFacadeImpl;
import com.pengsoft.system.biz.service.MessageTemplateService;
import com.pengsoft.system.domain.entity.MessageTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link MessageTemplateFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class MessageTemplateFacadeImpl extends EntityFacadeImpl<MessageTemplateService, MessageTemplate, String> implements MessageTemplateFacade {

    @Override
    public Optional<MessageTemplate> findOneByCode(final String code) {
        return getService().findOneByCode(code);
    }

}
