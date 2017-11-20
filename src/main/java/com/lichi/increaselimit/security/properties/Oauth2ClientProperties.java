package com.lichi.increaselimit.security.properties;

import lombok.Data;

/**
 * Oauth2Client配置
 * @author majie
 *
 */
@Data
public class Oauth2ClientProperties {
	
	private String clientId;
	
	private String clientSecret;
	
	private int accessTokenValiditySeconds = 7200;   //不然不配置的话就是0，表示不过期
	
	private String[] grantTypes;
	
	private String[] scopes;
}
