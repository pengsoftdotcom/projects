package com.pengsoft.security.oauth.biz.repository;

import com.pengsoft.security.oauth.domain.entity.Client;
import com.pengsoft.security.oauth.domain.entity.QClient;
import com.pengsoft.support.biz.repository.EntityRepository;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The repository interface of {@link Client} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface ClientRepository extends EntityRepository<QClient, Client, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QClient root) {
        EntityRepository.super.customize(bindings, root);
        bindings.bind(root.code).first(StringPath::contains);
        bindings.bind(root.grantTypes).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link Client} with given code.
     *
     * @param code {@link Client}'s code
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Client> findOneByCode(@NotBlank String code);

}
