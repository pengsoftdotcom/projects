package com.pengsoft.system.biz.facade;

import com.pengsoft.support.biz.facade.TreeEntityFacade;
import com.pengsoft.system.biz.service.DictionaryItemService;
import com.pengsoft.system.domain.entity.DictionaryItem;

/**
 * The facade interface of {@link DictionaryItem}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DictionaryItemFacade extends TreeEntityFacade<DictionaryItemService, DictionaryItem, String>, DictionaryItemService {

}
