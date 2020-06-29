package com.pengsoft.support.domain.type;

import java.util.HashMap;

/**
 * map postgresql json user type
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class MapType extends JsonType {

    @Override
    public Class<?> returnedClass() {
        return HashMap.class;
    }

}
