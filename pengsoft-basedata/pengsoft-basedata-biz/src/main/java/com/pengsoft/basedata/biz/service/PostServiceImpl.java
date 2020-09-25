package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.PostRepository;
import com.pengsoft.basedata.domain.entity.Post;
import com.pengsoft.support.biz.service.TreeEntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        final var parentId = Optional.ofNullable(post.getParent()).map(Post::getId).orElse(null);
        getRepository().findOneByOrganizationIdAndParentIdAndName(post.getOrganization().getId(), parentId, post.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, post)) {
                throw getExceptions().constraintViolated("name", "Exists", post.getName());
            }
        });
        return super.save(post);
    }

}
