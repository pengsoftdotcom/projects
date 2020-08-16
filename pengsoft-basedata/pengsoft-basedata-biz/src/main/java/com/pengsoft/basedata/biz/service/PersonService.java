package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.service.EntityService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * The service interface of {@link Person}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface PersonService extends EntityService<Person, String> {

    /**
     * Returns an {@link Optional} of a {@link Person} with given mobile.
     *
     * @param mobile {@link Person}'s mobile
     */
    Optional<Person> findOneByMobile(@NotBlank String mobile);

    /**
     * Returns an {@link Optional} of a {@link Person} with given user.
     *
     * @param user {@link Person}'s user
     */
    Optional<Person> findOneByUser(@NotNull User user);

}
