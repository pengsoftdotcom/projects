package com.pengsoft.security.oauth.commons.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.io.IOException;

/**
 * {@link OAuth2AccessToken} JSON serializer
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class OAuth2AccessTokenSerializer extends JsonSerializer<OAuth2AccessToken> {

    @Override
    public void serialize(OAuth2AccessToken value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("value", value.getValue());
        gen.writeNumberField("expiredAt", value.getExpiration().getTime());
        gen.writeEndObject();
    }

}
