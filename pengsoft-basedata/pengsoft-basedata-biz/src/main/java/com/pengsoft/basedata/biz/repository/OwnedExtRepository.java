package com.pengsoft.basedata.biz.repository;

import com.pengsoft.basedata.domain.entity.OwnedExt;
import com.pengsoft.security.biz.repository.OwnedRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.QueryHint;
import java.util.Collection;

/**
 * The repository interface of {@link OwnedExt} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@NoRepositoryBean
public interface OwnedExtRepository extends OwnedRepository {


    /**
     * Returns the count of entities with given ids and belongsTo.
     *
     * @param ids       The id collection
     * @param belongsTo The belongsTo
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    long countByIdInAndBelongsTo(Collection<String> ids, String belongsTo);

    /**
     * Returns the count of entities with given ids and controlledBy.
     *
     * @param ids          The entity's id
     * @param controlledBy The entity's controlledBy
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    long countByIdInAndControlledBy(Collection<String> ids, String controlledBy);

}
