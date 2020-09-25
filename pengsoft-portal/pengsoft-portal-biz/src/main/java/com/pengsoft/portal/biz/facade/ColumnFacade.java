package com.pengsoft.portal.biz.facade;


import com.pengsoft.portal.biz.service.ColumnService;
import com.pengsoft.portal.domain.entity.Column;
import com.pengsoft.support.biz.facade.TreeEntityFacade;

/**
 * The facade interface of {@link Column}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface ColumnFacade extends TreeEntityFacade<ColumnService, Column, String>, ColumnService {

}
