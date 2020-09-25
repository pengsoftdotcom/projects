package com.pengsoft.device.biz.service;

import com.pengsoft.device.biz.repository.ProductRepository;
import com.pengsoft.device.domain.entity.Product;
import com.pengsoft.support.biz.service.EntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import com.pengsoft.system.domain.entity.DictionaryItem;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link ProductService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class ProductServiceImpl extends EntityServiceImpl<ProductRepository, Product, String> implements ProductService {

    @Override
    public Product save(final Product product) {
        findOneByCode(product.getCode()).ifPresent(source -> {
            if (EntityUtils.ne(source, product)) {
                throw getExceptions().constraintViolated("code", "Exists", product.getCode());
            }
        });
        findOneByCategoryAndName(product.getCategory(), product.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, product)) {
                throw getExceptions().constraintViolated("name", "Exists", product.getName());
            }
        });
        return super.save(product);
    }

    @Override
    public Optional<Product> findOneByCode(final String code) {
        return getRepository().findOneByCode(code);
    }

    @Override
    public Optional<Product> findOneByCategoryAndName(final DictionaryItem category, final String name) {
        return getRepository().findOneByCategoryIdAndName(category.getId(), name);
    }

}
