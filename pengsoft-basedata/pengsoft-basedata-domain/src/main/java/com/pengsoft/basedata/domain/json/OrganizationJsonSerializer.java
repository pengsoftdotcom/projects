package com.pengsoft.basedata.domain.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pengsoft.basedata.domain.entity.Department;

import java.io.IOException;

/**
 * The {@link JsonSerializer} of {@link Department}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class DepartmentJsonSerializer extends JsonSerializer<Department> {

    @Override
    public void serialize(final Department department, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", department.getId());
        gen.writeStringField("name", department.getName());
        gen.writeEndObject();
    }

}
