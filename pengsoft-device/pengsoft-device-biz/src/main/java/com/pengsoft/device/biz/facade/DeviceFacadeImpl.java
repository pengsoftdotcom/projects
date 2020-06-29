package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.DeviceService;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.Product;
import com.pengsoft.device.domain.entity.PurchaseBatchItem;
import com.pengsoft.support.biz.facade.EntityFacadeImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link DeviceFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class DeviceFacadeImpl extends EntityFacadeImpl<DeviceService, Device, String> implements DeviceFacade {

    @Override
    public void activate(final String code) {
        getService().activate(code);
    }

    @Override
    public void online(final Device device) {
        getService().online(device);
    }

    @Override
    public void offline(final Device device) {
        getService().offline(device);
    }

    @Override
    public void pushConfig(final Device device) {
        getService().pushConfig(device);
    }

    @Override
    public void pushTime(final Device device) {
        getService().pushTime(device);
    }

    @Override
    public Optional<Device> findOneByCode(final String code) {
        return getService().findOneByCode(code);
    }

    @Override
    public Optional<Device> findFirstByActivatedFalseAndProductAndCodeStartsWith(final Product product, final String code) {
        return getService().findFirstByActivatedFalseAndProductAndCodeStartsWith(product, code);
    }

    @Override
    public long countByPurchaseBatchItemAndActivatedTrue(final PurchaseBatchItem purchaseBatchItem) {
        return getService().countByPurchaseBatchItemAndActivatedTrue(purchaseBatchItem);
    }

}
