package com.lichi.increaselimit.security.config.auth;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MIN_VALUE)
public class AutentiationConfigProvider implements AuthorizeConfigProvider {

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		try {
			config.
				antMatchers("/","/login.html","/captcha-image","/v2/**","/swagger**", "/druid/**","/swagger-resources/**",
			    		"/social/signUp","/authentication/require","/code/sms","/webjars/**","/circle/article/**","/sysuser/regiter").
				permitAll().and().csrf().disable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
