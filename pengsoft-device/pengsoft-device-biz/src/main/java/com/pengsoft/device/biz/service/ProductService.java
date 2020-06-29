package com.pengsoft.device.biz.service;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pengsoft.device.domain.entity.Product;
import com.pengsoft.support.biz.service.EntityService;
import com.pengsoft.system.domain.entity.DictionaryItem;

/**
 * The service interface of {@link Product}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface ProductService extends EntityService<Product, String> {

    /**
     * Returns an {@link Optional} of a {@link Product} with given code.
     *
     * @param code {@link Product}'s code
     */
    Optional<Product> findOneByCode(@NotBlank String code);

    /**
     * Returns an {@link Optional} of a {@link Product} with given category and name.
     *
     * @param category {@link Product}'s category
     * @param name {@link Product}'s name
     */
    Optional<Product> findOneByCategoryAndName(@NotNull DictionaryItem category, @NotBlank String name);

}
