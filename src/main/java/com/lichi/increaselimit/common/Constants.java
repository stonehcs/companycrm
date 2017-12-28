package com.lichi.increaselimit.common;

/**
 * 常量
 * @author majie
 *
 */
public interface Constants {
	
	/**
	 * 手机验证码存放key
	 */
	public static final String MOBILE_REDIS_KEY = "code:sms:";
	/**
	 * 登录的客服
	 */
	public static final String LOGIN_KEFU = "login_kefu:";
	/**
	 * 客服好友
	 */
	public static final String LOGIN_KEFU_FRIENDS = "login_kefu:friends:";
	/**
	 * 登录的用户
	 */
	public static final String LOGIN_USER = "login_user:";
	
	/**
	 * 登录的系统用户
	 */
	public static final String LOGIN_SYS_USER = "login_sys_user:";
	
	/**
	 * 资源
	 */
	public static final String RESOURCE = "resource:";
	/**
	 * 验证码存放在redis的时间900s
	 */
	public static final Long CODE_IN_REDIS_TIME = 900L;
	/**
	 * 发送验证码的间隔时间
	 */
	public static final int CODE_RESEND = 60;
	

}
