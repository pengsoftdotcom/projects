package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.support.biz.service.EntityService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * The service interface of {@link Staff}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface StaffService extends EntityService<Staff, String> {

    /**
     * Set the primary job.
     *
     * @param person The {@link Person}.
     * @param job    The primary {@link Job}.
     */
    void setPrimaryJob(@NotNull Person person, @NotNull Job job);

    /**
     * Returns an {@link Optional} of a {@link Staff} with given person and job.
     *
     * @param person {@link Staff}'s person
     * @param job    {@link Staff}'s job
     */
    Optional<Staff> findOneByPersonAndJob(@NotNull Person person, @NotNull Job job);

    /**
     * Returns an {@link Optional} of a {@link Staff} with given person and primary true.
     *
     * @param person {@link Staff}'s person
     */
    Optional<Staff> findOneByPersonAndPrimaryTrue(@NotNull Person person);

    /**
     * Returns all {@link Staff}s with given person.
     *
     * @param person {@link Staff}'s person
     */
    List<Staff> findAllByPerson(@NotNull Person person);

    /**
     * Returns all {@link Staff}s with given jobs
     *
     * @param jobs The {@link Staff}'s job
     */
    List<Staff> findAllByJobIn(@NotEmpty List<Job> jobs);

}
