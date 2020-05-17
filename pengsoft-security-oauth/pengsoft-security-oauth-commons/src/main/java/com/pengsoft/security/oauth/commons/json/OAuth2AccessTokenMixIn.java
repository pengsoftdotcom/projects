package com.pengsoft.security.oauth.commons.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * To replace the original JSON serializer.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@JsonSerialize(using = OAuth2AccessTokenSerializer.class)
public class OAuth2AccessTokenMixIn {

}
