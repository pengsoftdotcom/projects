package com.pengsoft.system.biz.facade;

import com.pengsoft.support.biz.facade.TreeEntityFacadeImpl;
import com.pengsoft.system.biz.service.DictionaryItemService;
import com.pengsoft.system.domain.entity.DictionaryItem;
import com.pengsoft.system.domain.entity.DictionaryType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The implementer of {@link DictionaryItemFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class DictionaryItemFacadeImpl extends TreeEntityFacadeImpl<DictionaryItemService, DictionaryItem, String> implements DictionaryItemFacade {

    @Override
    public List<DictionaryItem> findAllByTypeCode(final String code) {
        return getService().findAllByTypeCode(code);
    }

    @Override
    public Optional<DictionaryItem> findOneByTypeAndParentAndCode(final DictionaryType type, final DictionaryItem parent, final String code) {
        return getService().findOneByTypeAndParentAndCode(type, parent, code);
    }

}
