package com.lichi.increaselimit.security.validate.code.sms;

/**
 * 发送短信接口
 * @author majie
 *
 */
public interface SmsCodeSender {
	
	/**
	 * 发送短息验证码
	 * @param mobile
	 * @param code
	 */
	void send(String mobile, String code);

}
