package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.BatchService;
import com.pengsoft.device.domain.entity.Batch;
import com.pengsoft.support.biz.facade.BeanFacadeImpl;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link BatchFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class BatchFacadeImpl extends BeanFacadeImpl<BatchService, Batch, String> implements BatchFacade {

}
