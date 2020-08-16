package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.PostService;
import com.pengsoft.basedata.domain.entity.Post;
import com.pengsoft.support.biz.facade.TreeEntityFacadeImpl;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link PostFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class PostFacadeImpl extends TreeEntityFacadeImpl<PostService, Post, String> implements PostFacade {

}
