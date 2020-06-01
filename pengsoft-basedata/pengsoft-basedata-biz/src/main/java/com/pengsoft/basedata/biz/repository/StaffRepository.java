package com.pengsoft.basedata.biz.repository;

import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.QStaff;
import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.basedata.domain.entity.UserProfile;
import com.pengsoft.support.biz.repository.BeanRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
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
public interface StaffRepository extends BeanRepository<QStaff, Staff, String> {

    /**
     * Returns an {@link Optional} of a {@link Staff} with given user profile and job.
     *
     * @param userProfile {@link Staff}'s userProfile
     * @param job         {@link Staff}'s job
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Staff> findOneByUserProfileAndJob(@NotNull UserProfile userProfile, @NotNull Job job);

    /**
     * Returns an {@link Optional} of a {@link Staff} with given user profile and major true.
     *
     * @param userProfile {@link Staff}'s userProfile
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<Staff> findOneByUserProfileAndMajorTrue(@NotNull UserProfile userProfile);

    /**
     * Returns all {@link Staff}s with given user profile.
     *
     * @param userProfile {@link Staff}'s userProfile
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    List<Staff> findAllByUserProfile(@NotNull UserProfile userProfile);

    /**
     * Returns all {@link Staff}s with given jobs
     *
     * @param jobs The {@link Staff}'s job
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    List<Staff> findAllByJobIn(@NotEmpty List<Job> jobs);

}
