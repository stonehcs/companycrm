package com.lichi.increaselimit.security.config.auth;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MAX_VALUE)
public class SysAuthConfigProvider implements AuthorizeConfigProvider {

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		System.out.println("-------------------------");
		config.anyRequest().access("@rbacService.hasPermission(request,authentication)");
		
	}

}
