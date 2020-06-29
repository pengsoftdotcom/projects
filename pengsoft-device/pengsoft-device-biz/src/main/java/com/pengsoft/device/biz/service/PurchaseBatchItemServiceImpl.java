package com.pengsoft.device.biz.service;

import com.pengsoft.device.biz.repository.PurchaseBatchItemRepository;
import com.pengsoft.device.domain.entity.PurchaseBatchItem;
import com.pengsoft.support.biz.service.EntityServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link PurchaseBatchItemService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class PurchaseBatchItemServiceImpl extends EntityServiceImpl<PurchaseBatchItemRepository, PurchaseBatchItem, String> implements PurchaseBatchItemService {

}
