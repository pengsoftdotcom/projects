package com.pengsoft.system.biz.facade;

import com.pengsoft.support.biz.facade.EntityFacade;
import com.pengsoft.system.biz.service.DictionaryTypeService;
import com.pengsoft.system.domain.entity.DictionaryType;

/**
 * The facade interface of {@link DictionaryType}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DictionaryTypeFacade extends EntityFacade<DictionaryTypeService, DictionaryType, String>, DictionaryTypeService {

}
