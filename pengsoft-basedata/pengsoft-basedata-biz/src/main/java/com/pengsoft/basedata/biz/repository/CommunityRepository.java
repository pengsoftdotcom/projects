package com.pengsoft.basedata.biz.repository;

import com.pengsoft.basedata.domain.entity.Community;
import com.pengsoft.basedata.domain.entity.QCommunity;
import com.pengsoft.support.biz.repository.TreeEntityRepository;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The repository interface of {@link Community} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface CommunityRepository extends TreeEntityRepository<QCommunity, Community, String>, OwnedExtRepository {

    @Override
    default void customize(final QuerydslBindings bindings, final QCommunity root) {
        TreeEntityRepository.super.customize(bindings, root);
        bindings.bind(root.name).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link Community} with given parent id, region id and name.
     *
     * @param parentId The id of {@link Community}'s parent
     * @param regionId The id of {@link Community}'s region
     * @param name     {@link Community}'s name
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Community> findOneByParentIdAndRegionIdAndName(String parentId, @NotBlank String regionId, @NotBlank String name);

}
