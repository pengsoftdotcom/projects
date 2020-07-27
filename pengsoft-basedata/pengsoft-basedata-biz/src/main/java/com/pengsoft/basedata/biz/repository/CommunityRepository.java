package com.pengsoft.basedata.biz.repository;

import com.pengsoft.basedata.domain.entity.Community;
import com.pengsoft.basedata.domain.entity.QCommunity;
import com.pengsoft.support.biz.repository.EntityRepository;
import com.pengsoft.system.domain.entity.Region;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * The repository interface of {@link Community} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface CommunityRepository extends EntityRepository<QCommunity, Community, String>, OwnedExtRepository {

    @Override
    default void customize(final QuerydslBindings bindings, final QCommunity root) {
        EntityRepository.super.customize(bindings, root);
        bindings.bind(root.name).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link Community} with given region and name.
     *
     * @param region {@link Community}'s region
     * @param name   {@link Community}'s name
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Community> findOneByRegionAndName(@NotNull Region region, @NotBlank String name);

}
