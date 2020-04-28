package com.pengsoft.security.biz.repository;

import com.pengsoft.security.domain.entity.QUser;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.repository.BeanRepository;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The repository interface of {@link User} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface UserRepository extends BeanRepository<QUser, User, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QUser root) {
        BeanRepository.super.customize(bindings, root);
        bindings.bind(root.username).first(StringPath::contains);
    }

    /**
     * Reset password.
     *
     * @param id       The user id
     * @param password The password to be reset.
     */
    @Modifying
    @Query("update User set password = ?2 where id = ?1")
    void resetPassword(@NotBlank final String id, @NotBlank final String password);

    /**
     * Returns an {@link Optional} of a {@link User} with given username.
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<User> findOneByUsername(@NotBlank String username);

}
