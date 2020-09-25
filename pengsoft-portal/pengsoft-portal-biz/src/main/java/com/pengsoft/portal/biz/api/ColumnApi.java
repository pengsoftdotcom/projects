package com.pengsoft.portal.biz.api;

import com.pengsoft.portal.biz.facade.ColumnFacade;
import com.pengsoft.portal.domain.entity.Column;
import com.pengsoft.support.biz.api.TreeEntityApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Column}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/column")
public class ColumnApi extends TreeEntityApi<ColumnFacade, Column, String> {

}