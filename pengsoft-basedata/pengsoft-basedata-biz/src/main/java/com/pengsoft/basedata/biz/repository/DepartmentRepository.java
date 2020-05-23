package com.pengsoft.basedata.biz.repository;

import com.pengsoft.basedata.domain.entity.Department;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.basedata.domain.entity.QDepartment;
import com.pengsoft.support.biz.repository.TreeBeanRepository;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * The repository interface of {@link Department} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface DepartmentRepository extends TreeBeanRepository<QDepartment, Department, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QDepartment root) {
        TreeBeanRepository.super.customize(bindings, root);
        bindings.bind(root.name).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link Department} with given organization, parent and name.
     *
     * @param organization {@link Department}'s organization
     * @param parent       {@link Department}'s parent
     * @param name         {@link Department}'s name
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Organization> findOneByOrganizationAndParentAndName(@NotNull Organization organization, Department parent, @NotBlank String name);

}
