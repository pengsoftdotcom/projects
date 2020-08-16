package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.CommunityRepository;
import com.pengsoft.basedata.domain.entity.Community;
import com.pengsoft.support.biz.service.TreeEntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link CommunityService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class CommunityServiceImpl extends TreeEntityServiceImpl<CommunityRepository, Community, String> implements CommunityService {

    @Override
    public Community save(final Community community) {
        getRepository().findOneByParentAndRegionAndName(community.getParent(), community.getRegion(), community.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, community)) {
                throw getExceptions().constraintViolated("name", "Exists", community.getName());
            }
        });
        return super.save(community);
    }

}
