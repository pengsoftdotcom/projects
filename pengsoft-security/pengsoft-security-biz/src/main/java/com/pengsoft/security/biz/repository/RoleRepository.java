package com.pengsoft.security.biz.repository;

import com.pengsoft.security.domain.entity.QRole;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.support.biz.repository.TreeBeanRepository;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The repository interface of {@link Role} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface RoleRepository extends TreeBeanRepository<QRole, Role, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QRole root) {
        TreeBeanRepository.super.customize(bindings, root);
        bindings.bind(root.code).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link Role} with given code.
     *
     * @param code {@link Role}'s code
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Role> findOneByCode(@NotBlank String code);

}
