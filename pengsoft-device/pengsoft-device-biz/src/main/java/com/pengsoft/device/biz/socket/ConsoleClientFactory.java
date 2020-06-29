package com.pengsoft.device.biz.socket;

import com.pengsoft.device.biz.repository.DeviceRepository;
import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.DeviceConfig;
import com.pengsoft.support.commons.json.ObjectMapper;
import com.pengsoft.support.commons.util.DateUtils;
import com.pengsoft.support.commons.util.StringUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Console client factory
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Named
public class ConsoleClientFactory {

    private final Map<String, ConsoleClient> clients = new HashMap<>();

    @Inject
    private List<ResponseHandler> handlers;

    public ConsoleClientFactory(final DeviceRepository deviceRepository, final ObjectMapper objectMapper) {
        deviceRepository.findAll().forEach(device -> {
            final var client = new ConsoleClient();
            client.setDevice(device);
            client.setHost(device.getHost());
            client.setPort(device.getPort());
            client.setHandlers(handlers);
            client.setObjectMapper(objectMapper);
            client.start();
            clients.put(device.getCode(), client);
        });
    }

    public void pushConfig(final List<DeviceConfig> deviceConfigs) {
        deviceConfigs.stream()
                .filter(deviceConfig -> StringUtils.isNotBlank(deviceConfig.getValue()))
                .collect(Collectors.toMap(DeviceConfig::getDevice, List::of,
                        (final List<DeviceConfig> oldList, final List<DeviceConfig> newList) -> {
                            final var result = new ArrayList<DeviceConfig>();
                            result.addAll(oldList);
                            result.addAll(newList);
                            return result;
                        }))
                .forEach((device, value) -> {
                    final var data = value.stream().collect(Collectors.toMap(DeviceConfig::getCode, deviceConfig -> (Object) switch (deviceConfig.getType()) {
                        case "int" -> Integer.parseInt(deviceConfig.getValue());
                        case "float" -> Float.parseFloat(deviceConfig.getValue());
                        default -> deviceConfig.getValue();
                    }));
                    final var req = new Request();
                    req.setType("set_config_req");
                    req.setSn(device.getCode());
                    req.setData(data);
                    clients.entrySet().stream().filter(e -> e.getKey().endsWith(device.getCode())).map(Map.Entry::getValue).findFirst().ifPresent(client -> client.write(req));
                });
    }

    public void pushTime(final Device device) {
        final var req = new Request();
        req.setType("set_time_req");
        req.setSn(device.getCode());
        req.setData(Map.of("ts", DateUtils.formatDateTime(DateUtils.currentDateTime())));
        clients.entrySet().stream().filter(e -> e.getKey().endsWith(device.getCode())).map(Map.Entry::getValue).findFirst().ifPresent(client -> client.write(req));
    }

}
