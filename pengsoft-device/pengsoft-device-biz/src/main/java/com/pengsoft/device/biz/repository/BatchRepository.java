package com.pengsoft.device.biz.repository;

import com.pengsoft.device.domain.entity.Batch;
import com.pengsoft.device.domain.entity.QBatch;
import com.pengsoft.support.biz.repository.BeanRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository interface of {@link Batch} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface BatchRepository extends BeanRepository<QBatch, Batch, String> {

}
