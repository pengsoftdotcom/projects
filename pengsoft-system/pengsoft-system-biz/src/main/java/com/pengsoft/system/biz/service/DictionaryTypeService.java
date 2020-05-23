package com.pengsoft.system.biz.service;

import com.pengsoft.support.biz.service.BeanService;
import com.pengsoft.system.domain.entity.DictionaryType;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The service interface of {@link DictionaryType}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DictionaryTypeService extends BeanService<DictionaryType, String> {

    /**
     * Returns an {@link Optional} of a {@link DictionaryType} with given code.
     *
     * @param code {@link DictionaryType}'s code
     */
    Optional<DictionaryType> findOneByCode(@NotBlank String code);

}
