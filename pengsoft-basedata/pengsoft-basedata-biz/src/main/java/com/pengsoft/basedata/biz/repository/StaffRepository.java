package com.pengsoft.basedata.biz.repository;

import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.basedata.domain.entity.QStaff;
import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.support.biz.repository.EntityRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * The repository interface of {@link Staff} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface StaffRepository extends EntityRepository<QStaff, Staff, String>, OwnedExtRepository {

    /**
     * Returns an {@link Optional} of a {@link Staff} with given person and job.
     *
     * @param person {@link Staff}'s person
     * @param job    {@link Staff}'s job
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Staff> findOneByPersonAndJob(@NotNull Person person, @NotNull Job job);

    /**
     * Returns an {@link Optional} of a {@link Staff} with given person id and primary true.
     *
     * @param personId The id of {@link Staff}'s person
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Staff> findOneByPersonIdAndPrimaryTrue(@NotBlank String personId);

    /**
     * Returns all {@link Staff}s with given person id.
     *
     * @param personId The id of {@link Staff}'s person
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    List<Staff> findAllByPersonId(@NotBlank String personId);

    /**
     * Returns all {@link Staff}s with given job ids
     *
     * @param jobIds The id of {@link Staff}'s job
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    List<Staff> findAllByJobIdIn(@NotEmpty List<String> jobIds);

}
