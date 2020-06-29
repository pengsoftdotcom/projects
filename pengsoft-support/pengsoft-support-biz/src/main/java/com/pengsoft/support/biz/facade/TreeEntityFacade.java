package com.pengsoft.support.biz.facade;

import com.pengsoft.support.biz.service.TreeEntityService;
import com.pengsoft.support.domain.entity.TreeEntity;

import java.io.Serializable;

/**
 * The facade interface of {@link TreeEntity}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface TreeEntityFacade<S extends TreeEntityService<T, ID>, T extends TreeEntity<T, ID>, ID extends Serializable>
        extends TreeEntityService<T, ID>, EntityFacade<S, T, ID> {

}
