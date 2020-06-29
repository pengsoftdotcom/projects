package com.pengsoft.device.biz.service;

import com.pengsoft.device.domain.entity.Product;
import com.pengsoft.device.domain.entity.ProductConfig;
import com.pengsoft.support.biz.service.BeanService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * The service interface of {@link ProductConfig}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface ProductConfigService extends BeanService<ProductConfig, String> {

    /**
     * Returns an {@link Optional} of a {@link ProductConfig} with given product and code.
     *
     * @param product {@link ProductConfig}'s product
     * @param code    {@link ProductConfig}'s code
     */
    Optional<ProductConfig> findOneByProductAndCode(@NotNull Product product, @NotBlank String code);

    /**
     * Returns an {@link Optional} of a {@link ProductConfig} with given product and name.
     *
     * @param product {@link ProductConfig}'s product
     * @param name    {@link ProductConfig}'s name
     */
    Optional<ProductConfig> findOneByProductAndName(@NotNull Product product, @NotBlank String name);

}
