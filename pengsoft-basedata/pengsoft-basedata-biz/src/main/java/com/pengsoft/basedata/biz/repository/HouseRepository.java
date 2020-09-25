package com.pengsoft.basedata.biz.repository;

import com.pengsoft.basedata.domain.entity.House;
import com.pengsoft.basedata.domain.entity.QHouse;
import com.pengsoft.support.biz.repository.EntityRepository;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The repository interface of {@link House} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface HouseRepository extends EntityRepository<QHouse, House, String>, OwnedExtRepository {

    @Override
    default void customize(final QuerydslBindings bindings, final QHouse root) {
        EntityRepository.super.customize(bindings, root);
        bindings.bind(root.name).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link House} with given floor id and code.
     *
     * @param floorId The id of {@link House}'s floor
     * @param code    {@link House}'s code
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<House> findOneByFloorIdAndCode(@NotBlank String floorId, @NotBlank String code);

}
