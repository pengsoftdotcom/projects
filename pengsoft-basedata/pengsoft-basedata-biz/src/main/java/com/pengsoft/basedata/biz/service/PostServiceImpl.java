package com.pengsoft.basedata.biz.service;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.pengsoft.basedata.biz.repository.PostRepository;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.basedata.domain.entity.Post;
import com.pengsoft.support.biz.service.TreeEntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;

/**
 * The implementer of {@link PostService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class PostServiceImpl extends TreeEntityServiceImpl<PostRepository, Post, String> implements PostService {

    @Override
    public Post save(final Post post) {
        findOneByOrganizationAndParentAndName(post.getOrganization(), post.getParent(), post.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, post)) {
                throw getExceptions().constraintViolated("name", "Exists", post.getName());
            }
        });
        return super.save(post);
    }

    @Override
    public Optional<Post> findOneByOrganizationAndParentAndName(final Organization organization, final Post parent, final String name) {
        return getRepository().findOneByOrganizationAndParentAndName(organization, parent, name);
    }

}
