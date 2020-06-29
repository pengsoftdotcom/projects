package com.pengsoft.device.biz.repository;

import java.util.Optional;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.pengsoft.basedata.biz.repository.OwnedExtRepository;
import com.pengsoft.device.domain.entity.PurchaseBatch;
import com.pengsoft.device.domain.entity.QPurchaseBatch;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.repository.EntityRepository;

/**
 * The repository interface of {@link PurchaseBatch} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since  1.0.0
 */
@Repository
public interface PurchaseBatchRepository extends EntityRepository<QPurchaseBatch, PurchaseBatch, String>, OwnedExtRepository {

    /**
     * Returns an {@link Optional} of a {@link User} with given name.
     *
     * @param name {@link PurchaseBatch}'s name
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<User> findOneByName(@NotBlank String name);

}
