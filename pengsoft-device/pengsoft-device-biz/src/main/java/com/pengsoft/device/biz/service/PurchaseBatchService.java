package com.pengsoft.device.biz.service;

import com.pengsoft.device.domain.entity.PurchaseBatch;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.service.EntityService;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The service interface of {@link PurchaseBatch}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface PurchaseBatchService extends EntityService<PurchaseBatch, String> {

    /**
     * Returns an {@link Optional} of a {@link User} with given name.
     *
     * @param name {@link PurchaseBatch}'s name
     */
    Optional<User> findOneByName(@NotBlank String name);

}
