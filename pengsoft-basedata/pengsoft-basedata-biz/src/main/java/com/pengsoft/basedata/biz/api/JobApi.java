package com.pengsoft.basedata.biz.api;

import com.pengsoft.basedata.biz.facade.JobFacade;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.support.biz.api.TreeBeanApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Job}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/job")
public class JobApi extends TreeBeanApi<JobFacade, Job, String> {

}
