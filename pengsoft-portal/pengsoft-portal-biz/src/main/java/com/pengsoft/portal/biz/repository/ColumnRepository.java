package com.pengsoft.portal.biz.repository;

import com.pengsoft.basedata.biz.repository.OwnedExtRepository;
import com.pengsoft.portal.domain.entity.Column;
import com.pengsoft.portal.domain.entity.QColumn;
import com.pengsoft.support.biz.repository.TreeEntityRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository interface of {@link Column} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface ColumnRepository extends TreeEntityRepository<QColumn, Column, String>, OwnedExtRepository {


}
