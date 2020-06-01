package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.UserProfileService;
import com.pengsoft.basedata.domain.entity.UserProfile;
import com.pengsoft.support.biz.facade.BeanFacade;

/**
 * The facade interface of {@link UserProfile}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface UserProfileFacade extends BeanFacade<UserProfileService, UserProfile, String>, UserProfileService {

}
