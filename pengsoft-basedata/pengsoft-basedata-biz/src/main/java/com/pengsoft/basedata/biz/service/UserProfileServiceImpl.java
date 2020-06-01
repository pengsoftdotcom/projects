package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.UserProfileRepository;
import com.pengsoft.basedata.domain.entity.UserProfile;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.service.BeanServiceImpl;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link UserProfileService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class UserProfileServiceImpl extends BeanServiceImpl<UserProfileRepository, UserProfile, String> implements UserProfileService {

    @Override
    public UserProfile save(final UserProfile userProfile) {
        findOneByMobile(userProfile.getMobile()).ifPresent(source -> {
            if (EntityUtils.ne(source, userProfile)) {
                throw getExceptions().constraintViolated("mobile", "Exists", userProfile.getMobile());
            }
        });
        if (StringUtils.isBlank(userProfile.getNickname())) {
            userProfile.setNickname("*" + userProfile.getName().substring(1));
        }
        return super.save(userProfile);
    }

    @Override
    public Optional<UserProfile> findOneByMobile(final String mobile) {
        return getRepository().findOneByMobile(mobile);
    }

    @Override
    public Optional<UserProfile> findOneByUser(final User user) {
        return getRepository().findOneByUser(user);
    }

}
