package com.pengsoft.device.biz.api;

import com.pengsoft.device.biz.facade.BatchFacade;
import com.pengsoft.device.domain.entity.Batch;
import com.pengsoft.support.biz.api.BeanApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Batch}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/batch")
public class BatchApi extends BeanApi<BatchFacade, Batch, String> {

}
