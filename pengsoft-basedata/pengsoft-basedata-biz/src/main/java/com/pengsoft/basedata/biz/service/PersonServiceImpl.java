package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.PersonRepository;
import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.service.EntityServiceImpl;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link PersonService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class PersonServiceImpl extends EntityServiceImpl<PersonRepository, Person, String> implements PersonService {

    @Override
    public Person save(final Person person) {
        findOneByMobile(person.getMobile()).ifPresent(source -> {
            if (EntityUtils.ne(source, person)) {
                throw getExceptions().constraintViolated("mobile", "Exists", person.getMobile());
            }
        });
        if (StringUtils.isBlank(person.getNickname())) {
            person.setNickname("*" + person.getName().substring(1));
        }
        return super.save(person);
    }

    @Override
    public Optional<Person> findOneByMobile(final String mobile) {
        return getRepository().findOneByMobile(mobile);
    }

    @Override
    public Optional<Person> findOneByUser(final User user) {
        return getRepository().findOneByUser(user);
    }

}
