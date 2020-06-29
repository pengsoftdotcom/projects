package com.pengsoft.basedata.biz.repository;

import java.util.Optional;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.basedata.domain.entity.Post;
import com.pengsoft.basedata.domain.entity.QPost;
import com.pengsoft.support.biz.repository.TreeEntityRepository;
import com.querydsl.core.types.dsl.StringPath;

/**
 * The repository interface of {@link Post} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since  1.0.0
 */
@Repository
public interface PostRepository extends TreeEntityRepository<QPost, Post, String>, OwnedExtRepository {

    @Override
    default void customize(final QuerydslBindings bindings, final QPost root) {
        TreeEntityRepository.super.customize(bindings, root);
        bindings.bind(root.name).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link Post} with given organization, parent and name.
     *
     * @param organization {@link Post}'s organization
     * @param parent       {@link Post}'s parent
     * @param name         {@link Post}'s name
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Post> findOneByOrganizationAndParentAndName(@NotNull Organization organization, Post parent, @NotBlank String name);

}
