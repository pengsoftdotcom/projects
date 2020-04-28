package com.pengsoft.support.biz.facade;

import com.pengsoft.support.biz.service.TreeBeanService;
import com.pengsoft.support.domain.entity.TreeBeanable;

import java.io.Serializable;

/**
 * The facade interface of {@link TreeBeanable}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface TreeBeanFacade<S extends TreeBeanService<T, ID>, T extends TreeBeanable<T, ID>, ID extends Serializable>
        extends TreeBeanService<T, ID>, BeanFacade<S, T, ID> {

}
