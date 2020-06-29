package com.pengsoft.device.biz.socket;

import com.pengsoft.device.biz.facade.DeviceFacade;
import com.pengsoft.device.biz.facade.ProductFacade;
import com.pengsoft.device.biz.facade.PurchaseBatchItemFacade;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.DeviceConfig;
import com.pengsoft.device.domain.entity.Product;
import com.pengsoft.support.commons.exception.Exceptions;
import com.pengsoft.support.commons.util.DateUtils;
import com.pengsoft.support.commons.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Device login request handler.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Slf4j
@Named
public class LoginRequestHandler implements RequestHandler {

    public static final String PRODUCT_CODE = "pengsoft_face_recognition";

    private Product product;

    @Inject
    private ProductFacade productFacade;

    @Inject
    private PurchaseBatchItemFacade purchaseBatchItemFacade;

    @Inject
    private DeviceFacade deviceFacade;

    @Inject
    private Exceptions exceptions;

    @Override
    public String getType() {
        return "login_req";
    }

    @Override
    public Map<String, Object> handle(final Request req) {
        final var code = req.getSn();
        final var optional = deviceFacade.findOneByCode(code);
        final Device device;
        if (optional.isEmpty()) {
            if (product == null) {
                product = productFacade.findOneByCode(PRODUCT_CODE).orElseThrow(() -> exceptions.entityNotFound("pengsoft_face_recognition"));
            }
            device = deviceFacade.findFirstByActivatedFalseAndProductAndCodeStartsWith(product, "unactivated").orElse(null);
            if (device == null) {
                log.warn("Insufficient number of devices or deleted by mistake");
                return Map.of("status", 3);
            }
            device.setCode(code);
            device.setActivatedAt(DateUtils.currentDateTime());
            device.setActivated(true);
            device.setOnline(true);
            deviceFacade.save(device);
            final var purchaseBatchItem = device.getPurchaseBatchItem();
            purchaseBatchItem.setUsedQuantity(deviceFacade.countByPurchaseBatchItemAndActivatedTrue(purchaseBatchItem));
            purchaseBatchItemFacade.save(purchaseBatchItem);
        } else {
            device = optional.get();
        }
        return Map.of("status", 0, "config", device.getConfigs().stream()
                .filter(config -> StringUtils.isNotBlank(config.getValue()))
                .collect(Collectors.toMap(DeviceConfig::getCode, config -> switch (config.getType()) {
                    case "int" -> Integer.parseInt(config.getValue());
                    case "float" -> Float.parseFloat(config.getValue());
                    default -> config.getValue();
                })));
    }

}
