package com.pengsoft.device.biz.socket;

import com.pengsoft.device.biz.facade.DeviceFacade;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

/**
 * Device heart beat request handler.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Named
public class HeartbeatClientRequestHandler implements ClientRequestHandler {

    @Inject
    private DeviceFacade deviceFacade;

    @Override
    public String getType() {
        return "heart_beat_req";
    }

    @Override
    public Map<String, Object> handle(final Request request) {
        deviceFacade.findOneByCode(request.getSn()).ifPresent(device -> {
            final var data = request.getData();
            device.setHost((String) data.get("device_ip_address"));
            device.setPort(13202);
            device.setInfo(data);
            deviceFacade.save(device);
        });
        return Map.of();
    }

}
