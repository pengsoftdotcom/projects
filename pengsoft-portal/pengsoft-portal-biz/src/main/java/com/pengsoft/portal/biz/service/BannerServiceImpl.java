package com.pengsoft.portal.biz.service;

import com.pengsoft.portal.biz.repository.BannerRepository;
import com.pengsoft.portal.domain.entity.Banner;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.service.TreeEntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link BannerService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class BannerServiceImpl extends TreeEntityServiceImpl<BannerRepository, Banner, String> implements BannerService {

    @Override
    public Banner save(final Banner banner) {
        findOneByParentAndName(banner.getParent(), banner.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, banner)) {
                throw getExceptions().constraintViolated("name", "Exists", banner.getName());
            }
        });
        return super.save(banner);
    }

    @Override
    public Optional<User> findOneByParentAndName(final Banner parent, final String name) {
        return getRepository().findOneByParentAndName(parent, name);
    }

}
