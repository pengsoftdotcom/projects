package com.pengsoft.basedata.biz.api;

import com.pengsoft.basedata.biz.facade.PostFacade;
import com.pengsoft.basedata.domain.entity.Post;
import com.pengsoft.support.biz.api.TreeEntityApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Post}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/post")
public class PostApi extends TreeEntityApi<PostFacade, Post, String> {

}
