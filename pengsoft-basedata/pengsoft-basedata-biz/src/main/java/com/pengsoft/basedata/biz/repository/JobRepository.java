package com.pengsoft.basedata.biz.repository;

import java.util.Optional;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import com.pengsoft.basedata.domain.entity.Department;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.QJob;
import com.pengsoft.support.biz.repository.TreeEntityRepository;
import com.querydsl.core.types.dsl.StringPath;

/**
 * The repository interface of {@link Job} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since  1.0.0
 */
@Repository
public interface JobRepository extends TreeEntityRepository<QJob, Job, String>, OwnedExtRepository {

    @Override
    default void customize(final QuerydslBindings bindings, final QJob root) {
        TreeEntityRepository.super.customize(bindings, root);
        bindings.bind(root.name).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link Job} with given department, parent and name.
     *
     * @param department {@link Job}'s department
     * @param parent     {@link Job}'s parent
     * @param name       {@link Job}'s name
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Job> findOneByDepartmentAndParentAndName(@NotNull Department department, Job parent, @NotBlank String name);

}
