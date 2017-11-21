package com.lichi.increaselimit.security.social.qq.connet;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.lichi.increaselimit.security.social.qq.api.QQ;

/**
 * 重写连接工厂
 * @author majie
 *
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}
