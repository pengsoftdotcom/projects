package com.pengsoft.support.biz.facade;

import com.pengsoft.support.biz.service.EntityService;
import com.pengsoft.support.domain.entity.Entity;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * The facade interface of {@link Entity}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Validated
public interface EntityFacade<S extends EntityService<T, ID>, T extends Entity<ID>, ID extends Serializable> extends EntityService<T, ID> {

    S getService();

}
