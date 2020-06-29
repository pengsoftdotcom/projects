package com.pengsoft.device.biz.repository;

import com.pengsoft.device.domain.entity.PurchaseBatch;
import com.pengsoft.device.domain.entity.QPurchaseBatch;
import com.pengsoft.support.biz.repository.BeanRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository interface of {@link PurchaseBatch} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface PurchaseBatchRepository extends BeanRepository<QPurchaseBatch, PurchaseBatch, String> {

}
