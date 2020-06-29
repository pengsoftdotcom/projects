package com.pengsoft.security.biz.repository;

import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.Collection;

/**
 * The repository interface of {@link OwnedBean} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface OwnedRepository {

    /**
     * Returns the count of entities with given ids and createdBy
     *
     * @param ids       The entity's id
     * @param createdBy The entity's createdBy
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    long countByIdInAndCreatedBy(Collection<String> ids, String createdBy);

}
