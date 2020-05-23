package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.PostService;
import com.pengsoft.basedata.domain.entity.Post;
import com.pengsoft.support.biz.facade.TreeBeanFacade;

/**
 * The facade interface of {@link Post}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface PostFacade extends TreeBeanFacade<PostService, Post, String>, PostService {

}
