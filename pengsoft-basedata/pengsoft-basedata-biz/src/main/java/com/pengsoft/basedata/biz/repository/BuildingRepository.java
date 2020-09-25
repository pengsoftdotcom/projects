package com.pengsoft.basedata.biz.repository;

import com.pengsoft.basedata.domain.entity.Building;
import com.pengsoft.basedata.domain.entity.QBuilding;
import com.pengsoft.support.biz.repository.TreeEntityRepository;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The repository interface of {@link Building} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface BuildingRepository extends TreeEntityRepository<QBuilding, Building, String>, OwnedExtRepository {

    @Override
    default void customize(final QuerydslBindings bindings, final QBuilding root) {
        TreeEntityRepository.super.customize(bindings, root);
        bindings.bind(root.name).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link Building} with given community id, parent id and name.
     *
     * @param communityId The id of {@link Building}'s community
     * @param parentId    The id of{@link Building}'s parent
     * @param name        {@link Building}'s name
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Building> findOneByCommunityIdAndParentIdAndName(@NotBlank String communityId, String parentId, @NotBlank String name);

}
