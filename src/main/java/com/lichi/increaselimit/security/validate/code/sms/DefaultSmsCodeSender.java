package com.lichi.increaselimit.security.validate.code.sms;

import org.springframework.scheduling.annotation.Async;

/**
 * 发送短信
 * @author majie
 *
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

	@Override
	@Async
	public void send(String mobile, String code) {
		System.out.println("向手机"+mobile+"发送短信验证码"+code);
	}

}
