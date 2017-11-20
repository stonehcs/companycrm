package com.lichi.increaselimit.security.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lichi.increaselimit.security.properties.SecurityProperties;
import com.lichi.increaselimit.security.validate.code.image.ImageCodeGenerator;
import com.lichi.increaselimit.security.validate.code.sms.DefaultSmsCodeSender;
import com.lichi.increaselimit.security.validate.code.sms.SmsCodeSender;

/**
 * 验证码bean配置
 * 短信验证码或者是图片验证码
 * @author majie
 *
 */
@Configuration
public class ValidateCodeBeanConfig {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator(); 
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}

}
