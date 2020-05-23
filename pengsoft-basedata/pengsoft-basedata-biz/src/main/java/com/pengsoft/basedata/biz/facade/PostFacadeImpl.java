package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.PostService;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.basedata.domain.entity.Post;
import com.pengsoft.support.biz.facade.TreeBeanFacadeImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link PostFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class PostFacadeImpl extends TreeBeanFacadeImpl<PostService, Post, String> implements PostFacade {

    @Override
    public Optional<Post> findOneByOrganizationAndParentAndName(final Organization organization, final Post parent, final String name) {
        return getService().findOneByOrganizationAndParentAndName(organization, parent, name);
    }

}
