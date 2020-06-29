package com.pengsoft.device.biz.service;

import com.pengsoft.device.biz.repository.PurchaseBatchRepository;
import com.pengsoft.device.domain.entity.PurchaseBatch;
import com.pengsoft.support.biz.service.BeanServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link PurchaseBatchService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class PurchaseBatchServiceImpl extends BeanServiceImpl<PurchaseBatchRepository, PurchaseBatch, String> implements PurchaseBatchService {

}
