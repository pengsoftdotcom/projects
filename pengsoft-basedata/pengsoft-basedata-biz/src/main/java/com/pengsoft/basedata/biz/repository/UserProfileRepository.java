package com.pengsoft.basedata.biz.repository;

import com.pengsoft.basedata.domain.entity.QUserProfile;
import com.pengsoft.basedata.domain.entity.UserProfile;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.repository.BeanRepository;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * The repository interface of {@link UserProfile} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface UserProfileRepository extends BeanRepository<QUserProfile, UserProfile, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QUserProfile root) {
        BeanRepository.super.customize(bindings, root);
        bindings.bind(root.name).first(StringPath::contains);
        bindings.bind(root.nickname).first(StringPath::contains);
        bindings.bind(root.mobile).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link UserProfile} with given mobile.
     *
     * @param mobile {@link UserProfile}'s mobile
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<UserProfile> findOneByMobile(@NotBlank String mobile);

    /**
     * Returns an {@link Optional} of a {@link UserProfile} with given user.
     *
     * @param user {@link UserProfile}'s user
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<UserProfile> findOneByUser(@NotNull User user);

}
