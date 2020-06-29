package com.pengsoft.support.domain.type;


import com.pengsoft.support.commons.json.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * postgresql jsonb user type
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Slf4j
public abstract class JsonType implements UserType {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public int[] sqlTypes() {
        return new int[]{ Types.JAVA_OBJECT };
    }

    @Override
    public boolean equals(final Object x, final Object y) {
        return x != null && x.equals(y);
    }

    @Override
    public int hashCode(final Object x) {
        return x == null ? 0 : x.hashCode();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object nullSafeGet(final ResultSet rs, final String[] names, final SharedSessionContractImplementor session, final Object owner)
            throws SQLException {
        final var columnName = names[0];
        final var columnValue = rs.getString(columnName);
        log.debug("Result set column {} value is {}", columnName, columnValue);
        try {
            return columnValue == null ? null : objectMapper.readValue(columnValue, returnedClass());
        } catch (final Exception e) {
            log.error("reading json error", e);
        }
        return null;
    }

    @Override
    public void nullSafeSet(final PreparedStatement st, final Object value, final int index, final SharedSessionContractImplementor session)
            throws SQLException {
        if (value == null) {
            log.debug("Binding null to parameter {} ", index);
            st.setNull(index, Types.OTHER);
        } else {
            try {
                final var stringValue = objectMapper.writeValueAsString(value);
                log.debug("Binding {} to parameter {} ", stringValue, index);
                st.setObject(index, stringValue, Types.OTHER);
            } catch (final Exception e) {
                log.error("writing json error", e);
            }
        }

    }

    @Override
    public Object deepCopy(final Object value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(final Object value) {
        return (Serializable) deepCopy(value);
    }

    @Override
    public Object assemble(final Serializable cached, final Object owner) {
        return deepCopy(cached);
    }

    @Override
    public Object replace(final Object original, final Object target, final Object owner) {
        return deepCopy(original);
    }

}

