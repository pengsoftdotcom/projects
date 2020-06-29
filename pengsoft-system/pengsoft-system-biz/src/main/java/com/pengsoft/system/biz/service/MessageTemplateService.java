package com.pengsoft.system.biz.service;

import com.pengsoft.support.biz.service.EntityService;
import com.pengsoft.system.domain.entity.MessageTemplate;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The service interface of {@link MessageTemplate}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface MessageTemplateService extends EntityService<MessageTemplate, String> {

    /**
     * Returns an {@link Optional} of a {@link MessageTemplate} with given code.
     *
     * @param code {@link MessageTemplate}'s code
     */
    Optional<MessageTemplate> findOneByCode(@NotBlank String code);

}
