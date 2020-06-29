package com.pengsoft.system.biz.service;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.pengsoft.support.biz.service.EntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import com.pengsoft.system.biz.repository.DictionaryTypeRepository;
import com.pengsoft.system.domain.entity.DictionaryType;

/**
 * The implementer of {@link DictionaryTypeService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class DictionaryTypeServiceImpl extends EntityServiceImpl<DictionaryTypeRepository, DictionaryType, String> implements DictionaryTypeService {

    @Override
    public DictionaryType save(final DictionaryType type) {
        findOneByCode(type.getCode()).ifPresent(source -> {
            if (EntityUtils.ne(source, type)) {
                throw getExceptions().constraintViolated("code", type.getCode());
            }
        });
        return super.save(type);
    }

    @Override
    public Optional<DictionaryType> findOneByCode(final String code) {
        return getRepository().findOneByCode(code);
    }

}
