package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.BatchService;
import com.pengsoft.device.domain.entity.Batch;
import com.pengsoft.support.biz.facade.BeanFacade;

/**
 * The facade interface of {@link Batch}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface BatchFacade extends BeanFacade<BatchService, Batch, String>, BatchService {

}
