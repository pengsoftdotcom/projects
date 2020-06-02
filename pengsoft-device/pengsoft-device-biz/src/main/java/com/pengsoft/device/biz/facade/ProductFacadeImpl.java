package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.ProductService;
import com.pengsoft.device.domain.entity.Product;
import com.pengsoft.support.biz.facade.BeanFacadeImpl;
import com.pengsoft.system.domain.entity.DictionaryItem;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link ProductFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class ProductFacadeImpl extends BeanFacadeImpl<ProductService, Product, String> implements ProductFacade {

    @Override
    public Optional<Product> findOneByCode(final String code) {
        return getService().findOneByCode(code);
    }

    @Override
    public Optional<Product> findOneByCategoryAndName(final DictionaryItem category, final String name) {
        return getService().findOneByCategoryAndName(category, name);
    }

}
