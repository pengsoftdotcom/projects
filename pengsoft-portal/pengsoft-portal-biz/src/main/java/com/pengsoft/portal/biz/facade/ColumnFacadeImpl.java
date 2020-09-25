package com.pengsoft.portal.biz.facade;

import com.pengsoft.portal.biz.service.ColumnService;
import com.pengsoft.portal.domain.entity.Column;
import com.pengsoft.support.biz.facade.TreeEntityFacadeImpl;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link Column}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class ColumnFacadeImpl extends TreeEntityFacadeImpl<ColumnService, Column, String> implements ColumnFacade {
    
}
