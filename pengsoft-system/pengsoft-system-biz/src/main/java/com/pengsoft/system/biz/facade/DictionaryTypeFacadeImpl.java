package com.pengsoft.system.biz.facade;

import com.pengsoft.support.biz.facade.EntityFacadeImpl;
import com.pengsoft.system.biz.service.DictionaryTypeService;
import com.pengsoft.system.domain.entity.DictionaryType;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link DictionaryTypeFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class DictionaryTypeFacadeImpl extends EntityFacadeImpl<DictionaryTypeService, DictionaryType, String> implements DictionaryTypeFacade {

    @Override
    public Optional<DictionaryType> findOneByCode(final String code) {
        return getService().findOneByCode(code);
    }

}
