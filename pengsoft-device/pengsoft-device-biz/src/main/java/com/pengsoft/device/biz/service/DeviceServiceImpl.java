package com.pengsoft.device.biz.service;

import com.pengsoft.device.biz.repository.BatchRepository;
import com.pengsoft.device.domain.entity.Batch;
import com.pengsoft.support.biz.service.BeanServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link BatchService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class BatchServiceImpl extends BeanServiceImpl<BatchRepository, Batch, String> implements BatchService {

}
