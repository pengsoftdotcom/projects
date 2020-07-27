package com.pengsoft.basedata.biz.repository;

import com.pengsoft.basedata.domain.entity.Building;
import com.pengsoft.basedata.domain.entity.Floor;
import com.pengsoft.basedata.domain.entity.QFloor;
import com.pengsoft.support.biz.repository.EntityRepository;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * The repository interface of {@link Floor} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface FloorRepository extends EntityRepository<QFloor, Floor, String>, OwnedExtRepository {

    @Override
    default void customize(final QuerydslBindings bindings, final QFloor root) {
        EntityRepository.super.customize(bindings, root);
        bindings.bind(root.name).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link Floor} with given building and name.
     *
     * @param building {@link Floor}'s building
     * @param name     {@link Floor}'s name
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Floor> findOneByBuildingAndName(@NotNull Building building, @NotBlank String name);

}
