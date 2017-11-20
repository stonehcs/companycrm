package com.lichi.increaselimit.security.properties;

import lombok.Data;

/**
 * oauth2配置
 * @author majie
 *
 */
@Data
public class Oauth2Properties {

	private Oauth2ClientProperties[] clients = {};
	
	private String jwtSigningKey = "lichi";
}
