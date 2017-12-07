package com.lichi.increaselimit.common;

/**
 * 常亮
 * @author majie
 *
 */
public interface Constants {
	
	/**
	 * 手机验证码存放key
	 */
	public static final String MOBILE_REDIS_KEY = "code:sms:";
	/**
	 * 验证码存放在redis的时间900s
	 */
	public static final Long CODE_IN_REDIS_TIME = 900L;
	/**
	 * 发送验证码的间隔时间
	 */
	public static final int CODE_RESEND = 60;
}
