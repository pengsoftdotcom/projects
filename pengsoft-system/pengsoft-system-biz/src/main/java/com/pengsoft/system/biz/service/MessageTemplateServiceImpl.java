package com.pengsoft.system.biz.service;

import com.pengsoft.support.biz.service.EntityServiceImpl;
import com.pengsoft.system.biz.repository.MessageTemplateRepository;
import com.pengsoft.system.domain.entity.MessageTemplate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link MessageTemplateService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class MessageTemplateServiceImpl extends EntityServiceImpl<MessageTemplateRepository, MessageTemplate, String> implements MessageTemplateService {

    @Override
    public Optional<MessageTemplate> findOneByCode(final String code) {
        return getRepository().findOneByCode(code);
    }

}
