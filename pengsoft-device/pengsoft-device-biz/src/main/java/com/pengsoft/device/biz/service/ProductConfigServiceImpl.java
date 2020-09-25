package com.pengsoft.device.biz.service;

import com.pengsoft.device.biz.repository.ProductConfigRepository;
import com.pengsoft.device.domain.entity.Product;
import com.pengsoft.device.domain.entity.ProductConfig;
import com.pengsoft.support.biz.service.EntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link ProductConfigService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class ProductConfigServiceImpl extends EntityServiceImpl<ProductConfigRepository, ProductConfig, String> implements ProductConfigService {

    @Override
    public ProductConfig save(final ProductConfig productConfig) {
        findOneByProductAndCode(productConfig.getProduct(), productConfig.getCode()).ifPresent(source -> {
            if (EntityUtils.ne(source, productConfig)) {
                throw getExceptions().constraintViolated("code", "Exists", productConfig.getCode());
            }
        });
        findOneByProductAndName(productConfig.getProduct(), productConfig.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, productConfig)) {
                throw getExceptions().constraintViolated("name", "Exists", productConfig.getName());
            }
        });
        return super.save(productConfig);
    }

    @Override
    public Optional<ProductConfig> findOneByProductAndCode(final Product product, final String code) {
        return getRepository().findOneByProductIdAndCode(product.getId(), code);
    }

    @Override
    public Optional<ProductConfig> findOneByProductAndName(final Product product, final String name) {
        return getRepository().findOneByProductIdAndName(product.getId(), name);
    }

}
