package com.pengsoft.basedata.biz.repository;

import com.pengsoft.basedata.domain.entity.Floor;
import com.pengsoft.basedata.domain.entity.House;
import com.pengsoft.basedata.domain.entity.QHouse;
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
     * Returns an {@link Optional} of a {@link House} with given floor and code.
     *
     * @param floor {@link House}'s floor
     * @param code  {@link House}'s code
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<House> findOneByFloorAndCode(@NotNull Floor floor, @NotBlank String code);

}
