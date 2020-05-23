package com.pengsoft.system.biz.api;

import com.pengsoft.support.biz.api.BeanApi;
import com.pengsoft.system.biz.facade.DictionaryTypeFacade;
import com.pengsoft.system.domain.entity.DictionaryType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link DictionaryType}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/dictionary-type")
public class DictionaryTypeApi extends BeanApi<DictionaryTypeFacade, DictionaryType, String> {

}
