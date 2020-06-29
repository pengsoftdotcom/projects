package com.pengsoft.basedata.biz.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.basedata.domain.entity.QStaff;
import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.support.biz.repository.EntityRepository;

/**
 * The repository interface of {@link Staff} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since  1.0.0
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
     * Returns an {@link Optional} of a {@link Staff} with given person and primary true.
     *
     * @param person {@link Staff}'s person
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Staff> findOneByPersonAndPrimaryTrue(@NotNull Person person);

    /**
     * Returns all {@link Staff}s with given person.
     *
     * @param person {@link Staff}'s person
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    List<Staff> findAllByPerson(@NotNull Person person);

    /**
     * Returns all {@link Staff}s with given jobs
     *
     * @param jobs The {@link Staff}'s job
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    List<Staff> findAllByJobIn(@NotEmpty List<Job> jobs);

}
