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
	
	/**
	 * oauth2.0
	 */
	private Oauth2Properties oauth2Properties = new Oauth2Properties();
	
	/**
	 * 验证码
	 */
	private ValidateCodeProperties code = new ValidateCodeProperties();
	
	/**
	 * 社交登陆
	 */
	private SocialProperties social = new SocialProperties();
}
