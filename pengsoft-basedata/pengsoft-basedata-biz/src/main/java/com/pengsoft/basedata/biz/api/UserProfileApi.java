package com.pengsoft.basedata.biz.api;

import com.pengsoft.basedata.biz.facade.UserProfileFacade;
import com.pengsoft.basedata.domain.entity.UserProfile;
import com.pengsoft.support.biz.api.BeanApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link UserProfile}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/user-profile")
public class UserProfileApi extends BeanApi<UserProfileFacade, UserProfile, String> {

}
