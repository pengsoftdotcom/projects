package com.pengsoft.system.biz.messaging.sender;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.http.MethodType;
import com.pengsoft.support.commons.json.ObjectMapper;
import com.pengsoft.system.domain.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SMS message sender
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class SmsMessageSender implements MessageSender {

    private static final Logger log = LoggerFactory.getLogger(SmsMessageSender.class);

    private final DefaultAcsClient client;

    private final ObjectMapper objectMapper;

    public SmsMessageSender(final DefaultAcsClient client, final ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    @Override
    public void send(final Message message) throws Exception {
        final CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", client.getProfile().getRegionId());
        request.putQueryParameter("PhoneNumbers", message.getRecipient().getMobile());
        request.putQueryParameter("TemplateCode", message.getTemplate().getSmsCode());
        request.putQueryParameter("TemplateParam", objectMapper.writeValueAsString(message.getTemplate().getParams()));
        request.putQueryParameter("SignName", message.getTemplate().getSmsSignature());
//        final var response = client.getCommonResponse(request);
//        log.debug(response.getData());
    }

}
