package com.pengsoft.system.biz.api;

import com.pengsoft.support.biz.api.TreeEntityApi;
import com.pengsoft.system.biz.facade.DictionaryItemFacade;
import com.pengsoft.system.domain.entity.DictionaryItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The web api of {@link DictionaryItem}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/dictionary-item")
public class DictionaryItemApi extends TreeEntityApi<DictionaryItemFacade, DictionaryItem, String> {

    @GetMapping("find-all-by-type-code")
    public List<DictionaryItem> findAllByTypeCode(final String code) {
        return getFacade().findAllByTypeCode(code);
    }

}
