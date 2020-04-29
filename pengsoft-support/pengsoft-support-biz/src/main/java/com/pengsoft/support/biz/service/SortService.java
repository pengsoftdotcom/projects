package com.pengsoft.support.biz.service;

import com.pengsoft.support.domain.entity.Sortable;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Map;

/**
 * The sort service interface.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface SortService {

    /**
     * sort
     *
     * @param sortInfo {key: entity, value: sequence}
     */
    void sort(@NotEmpty Map<? extends Sortable<? extends Serializable>, Long> sortInfo);

}
