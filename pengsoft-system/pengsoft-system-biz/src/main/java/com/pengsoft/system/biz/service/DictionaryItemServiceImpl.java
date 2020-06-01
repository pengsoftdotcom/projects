package com.pengsoft.system.biz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.pengsoft.support.biz.service.TreeBeanServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import com.pengsoft.system.biz.repository.DictionaryItemRepository;
import com.pengsoft.system.domain.entity.DictionaryItem;
import com.pengsoft.system.domain.entity.DictionaryType;

/**
 * The implementer of {@link DictionaryItemService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class DictionaryItemServiceImpl extends TreeBeanServiceImpl<DictionaryItemRepository, DictionaryItem, String>
        implements DictionaryItemService {

    @Override
    public DictionaryItem save(final DictionaryItem item) {
        findOneByTypeAndParentAndCode(item.getType(), item.getParent(), item.getCode()).ifPresent(source -> {
            if (EntityUtils.ne(source, item)) {
                throw getExceptions().constraintViolated("code", item.getCode());
            }
        });
        return super.save(item);
    }

    @Override
    public List<DictionaryItem> findAllByTypeCode(final String code) {
        return getRepository().findAllByTypeCode(code);
    }

    @Override
    public Optional<DictionaryItem> findOneByTypeAndParentAndCode(final DictionaryType type, final DictionaryItem parent, final String code) {
        return getRepository().findOneByTypeAndParentAndCode(type, parent, code);
    }

}
