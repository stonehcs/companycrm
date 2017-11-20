package com.lichi.increaselimit.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * 系统配置文件
 * @author majie
 *
 */
@ConfigurationProperties(prefix="lichi")
@Data
public class SecurityProperties {
	
	private Oauth2Properties oauth2Properties = new Oauth2Properties();
	
	private ValidateCodeProperties code = new ValidateCodeProperties();
}
