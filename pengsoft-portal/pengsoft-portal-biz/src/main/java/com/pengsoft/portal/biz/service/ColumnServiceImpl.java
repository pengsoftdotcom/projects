package com.pengsoft.portal.biz.service;

import com.pengsoft.portal.biz.repository.ColumnRepository;
import com.pengsoft.portal.domain.entity.Column;
import com.pengsoft.support.biz.service.TreeEntityServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link ColumnService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class ColumnServiceImpl extends TreeEntityServiceImpl<ColumnRepository, Column, String> implements ColumnService {
    
}
