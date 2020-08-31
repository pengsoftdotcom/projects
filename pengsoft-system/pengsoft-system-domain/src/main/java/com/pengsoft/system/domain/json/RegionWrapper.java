package com.pengsoft.system.domain.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pengsoft.system.domain.entity.Region;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * This wrapper just for JSON serialization.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@JsonSerialize(using = RegionWrapperJsonSerializer.class)
@Getter
@RequiredArgsConstructor
public class RegionWrapper implements Serializable {

    private static final long serialVersionUID = 1105803452474874888L;
    private final Region region;

}
