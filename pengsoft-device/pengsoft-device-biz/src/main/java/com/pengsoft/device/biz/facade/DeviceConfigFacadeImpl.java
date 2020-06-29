package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.ProductConfigService;
import com.pengsoft.device.domain.entity.Product;
import com.pengsoft.device.domain.entity.ProductConfig;
import com.pengsoft.support.biz.facade.BeanFacadeImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link ProductConfigFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class ProductConfigFacadeImpl extends BeanFacadeImpl<ProductConfigService, ProductConfig, String> implements ProductConfigFacade {

    @Override
    public Optional<ProductConfig> findOneByProductAndCode(final Product product, final String code) {
        return getService().findOneByProductAndCode(product, code);
    }

    @Override
    public Optional<ProductConfig> findOneByProductAndName(final Product product, final String name) {
        return getService().findOneByProductAndName(product, name);
    }

}
