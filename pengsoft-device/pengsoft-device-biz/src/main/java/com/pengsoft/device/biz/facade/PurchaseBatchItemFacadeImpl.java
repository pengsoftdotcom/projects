package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.DeviceConfigService;
import com.pengsoft.device.biz.service.DeviceService;
import com.pengsoft.device.biz.service.ProductService;
import com.pengsoft.device.biz.service.PurchaseBatchItemService;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.DeviceConfig;
import com.pengsoft.device.domain.entity.PurchaseBatchItem;
import com.pengsoft.support.biz.facade.EntityFacadeImpl;
import com.pengsoft.support.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * The implementer of {@link PurchaseBatchItemFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class PurchaseBatchItemFacadeImpl extends EntityFacadeImpl<PurchaseBatchItemService, PurchaseBatchItem, String> implements PurchaseBatchItemFacade {

    @Inject
    private ProductService productService;

    @Inject
    private DeviceService deviceService;

    @Inject
    private DeviceConfigService deviceConfigService;

    @Override
    public PurchaseBatchItem save(final PurchaseBatchItem purchaseBatchItem) {
        super.save(purchaseBatchItem);
        final var count = deviceService.countByPurchaseBatchItemAndActivatedTrue(purchaseBatchItem);
        final var leftQuantity = purchaseBatchItem.getQuantity() - count;
        for (int i = 0; i < leftQuantity; i++) {
            final var device = new Device();
            final var code = "unactivated" + StringUtils.HYPHEN + System.currentTimeMillis();
            device.setCode(code);
            device.setName(code);
            device.setPurchaseBatchItem(purchaseBatchItem);
            deviceService.save(device);
            productService.findOne(purchaseBatchItem.getProduct().getId()).ifPresent(product -> product.getConfigs().forEach(productConfig -> {
                final var deviceConfig = new DeviceConfig();
                BeanUtils.copyProperties(productConfig, deviceConfig);
                deviceConfig.setDevice(device);
                deviceConfigService.save(deviceConfig);
                device.getConfigs().add(deviceConfig);
            }));
        }
        return purchaseBatchItem;
    }

}
