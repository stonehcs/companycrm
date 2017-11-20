package com.lichi.increaselimit.security.validate.code.sms;

/**
 * 发送短信接口
 * @author majie
 *
 */
public interface SmsCodeSender {
	
	void send(String mobile, String code);

}
