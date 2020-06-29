package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.JobService;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.support.biz.facade.TreeEntityFacade;

/**
 * The facade interface of {@link Job}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface JobFacade extends TreeEntityFacade<JobService, Job, String>, JobService {

}
