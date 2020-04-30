package com.pengsoft.security.domain.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.util.List;

/**
 * The {@link JsonSerializer} of {@link GrantedAuthority} list.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class GrantedAuthorityListSerializer extends JsonSerializer<List<GrantedAuthority>> {

    @Override
    public void serialize(final List<GrantedAuthority> authorities, final JsonGenerator gen, final SerializerProvider serializers)
            throws IOException {
        gen.writeStartArray();
        for (final GrantedAuthority authority : authorities) {
            gen.writeString(authority.getAuthority());
        }
        gen.writeEndArray();
    }

}
