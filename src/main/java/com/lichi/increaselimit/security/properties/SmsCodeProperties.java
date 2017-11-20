package com.lichi.increaselimit.security.properties;

/**
 * 短信验证码配置
 * @author majie
 *
 */
public class SmsCodeProperties {
	/**
	 * 长度
	 */
	private int length = 6;
	/**
	 * 过期时间60s
	 */
	private int expireIn = 60;
	
	/**
	 * 拦截要验证的url
	 */
	private String url;

	public int getLength() {
		return length;
	}
	public void setLength(int lenght) {
		this.length = lenght;
	}
	public int getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
