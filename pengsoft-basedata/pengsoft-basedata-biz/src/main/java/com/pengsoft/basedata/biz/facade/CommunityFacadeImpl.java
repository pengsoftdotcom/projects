package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.CommunityService;
import com.pengsoft.basedata.domain.entity.Community;
import com.pengsoft.support.biz.facade.EntityFacadeImpl;
import com.pengsoft.system.domain.entity.Region;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link CommunityFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class CommunityFacadeImpl extends EntityFacadeImpl<CommunityService, Community, String> implements CommunityFacade {

    @Override
    public Optional<Community> findOneByRegionAndName(final Region region, final String name) {
        return getService().findOneByRegionAndName(region, name);
    }

}
