package com.lichi.increaselimit.security.validate.code.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.lichi.increaselimit.security.properties.SecurityConstants;
import com.lichi.increaselimit.security.validate.code.ValidateCode;
import com.lichi.increaselimit.security.validate.code.impl.AbstractValidateCodeProcessor;

/**
 * 短信验证码处理器
 * 
 * @author majie
 *
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		
		//TODO:对手机号码进行正则匹配
		smsCodeSender.send(mobile, validateCode.getCode());
	}

}
