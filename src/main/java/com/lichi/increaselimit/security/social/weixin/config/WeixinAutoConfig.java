package com.lichi.increaselimit.security.social.weixin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import com.lichi.increaselimit.security.properties.SecurityProperties;
import com.lichi.increaselimit.security.properties.WeixinProperties;
import com.lichi.increaselimit.security.social.weixin.connet.WeixinConnectionFactory;

/**
 * 微信登录配置
 * 
 * @author majie
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "lichi.social.weixin", name = "app-id")
public class WeixinAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
	 * #createConnectionFactory()
	 */
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		WeixinProperties weixinConfig = securityProperties.getSocial().getWeixin();
		return new WeixinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
				weixinConfig.getAppSecret());
	}
	
//	@Bean({"connect/weixinConnect", "connect/weixinConnected"})
//	@ConditionalOnMissingBean(name = "weixinConnectedView")
//	public View weixinConnectedView() {
//		return new ConnectView();
//	}
	
}
