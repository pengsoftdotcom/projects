package com.pengsoft.system.biz.service;

import com.pengsoft.support.biz.service.TreeBeanService;
import com.pengsoft.system.domain.entity.DictionaryItem;
import com.pengsoft.system.domain.entity.DictionaryType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * The service interface of {@link DictionaryItem}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DictionaryItemService extends TreeBeanService<DictionaryItem, String> {

    /**
     * Returns a collection of {@link DictionaryItem} with given code.
     *
     * @param code {@link DictionaryType}'s code
     */
    List<DictionaryItem> findAllByTypeCode(@NotBlank String code);

    /**
     * Returns an {@link Optional} of a {@link DictionaryItem} with given {@linkplain DictionaryType type}, {@linkplain DictionaryItem parent} and code.
     *
     * @param type   {@link DictionaryType}
     * @param parent The parent {@link DictionaryItem}
     * @param code   {@link DictionaryType}'s code
     */
    Optional<DictionaryItem> findOneByTypeAndParentAndCode(@NotNull DictionaryType type, DictionaryItem parent, @NotBlank String code);

}
