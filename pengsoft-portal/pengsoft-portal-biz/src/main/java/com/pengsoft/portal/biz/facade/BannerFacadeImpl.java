package com.pengsoft.portal.biz.facade;

import com.pengsoft.portal.biz.service.BannerService;
import com.pengsoft.portal.domain.entity.Banner;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.facade.TreeEntityFacadeImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link BannerFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class BannerFacadeImpl extends TreeEntityFacadeImpl<BannerService, Banner, String> implements BannerFacade {

    @Override
    public Optional<User> findOneByParentAndName(final Banner parent, final String name) {
        return getService().findOneByParentAndName(parent, name);
    }

}
