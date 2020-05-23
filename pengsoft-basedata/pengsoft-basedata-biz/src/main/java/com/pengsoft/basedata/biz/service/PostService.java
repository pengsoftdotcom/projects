package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.basedata.domain.entity.Post;
import com.pengsoft.support.biz.service.TreeBeanService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * The service interface of {@link Post}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface PostService extends TreeBeanService<Post, String> {

    /**
     * Returns an {@link Optional} of a {@link Post} with given organization, parent and name.
     *
     * @param organization {@link Post}'s organization
     * @param parent       {@link Post}'s parent
     * @param name         {@link Post}'s name
     */
    Optional<Post> findOneByOrganizationAndParentAndName(@NotNull Organization organization, Post parent, @NotBlank String name);

}
