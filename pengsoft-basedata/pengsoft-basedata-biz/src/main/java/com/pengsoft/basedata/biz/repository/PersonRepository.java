package com.pengsoft.basedata.biz.repository;

import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.basedata.domain.entity.QPerson;
import com.pengsoft.support.biz.repository.EntityRepository;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The repository interface of {@link Person} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface PersonRepository extends EntityRepository<QPerson, Person, String>, OwnedExtRepository {

    @Override
    default void customize(final QuerydslBindings bindings, final QPerson root) {
        EntityRepository.super.customize(bindings, root);
        bindings.bind(root.name).first(StringPath::contains);
        bindings.bind(root.nickname).first(StringPath::contains);
        bindings.bind(root.mobile).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link Person} with given mobile.
     *
     * @param mobile {@link Person}'s mobile
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Person> findOneByMobile(@NotBlank String mobile);

    /**
     * Returns an {@link Optional} of a {@link Person} with given user id.
     *
     * @param userId The id of {@link Person}'s user.
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Person> findOneByUserId(@NotBlank String userId);

}
