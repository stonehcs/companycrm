package com.lichi.increaselimit.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.cors.CorsUtils;

import com.lichi.increaselimit.common.config.CorsControllerFilter;
//import com.lichi.increaselimit.common.config.CorsControllerFilter;
import com.lichi.increaselimit.security.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.lichi.increaselimit.security.config.auth.AuthorizeConfigManager;
import com.lichi.increaselimit.security.handler.LoginFailureHandler;
import com.lichi.increaselimit.security.handler.LoginSuccessHandler;
import com.lichi.increaselimit.security.validate.code.ValidateCodeSecurityConfig;

/**
 * 资源认证服务器
 * 
 * @author majie
 *
 */
@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginSuccessHandler loginSuccessHandler;

	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;

	@Autowired
	private LoginFailureHandler loginFailureHandler;

	@Autowired
	private CorsControllerFilter corsControllerFilter;

	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private AuthorizeConfigManager authorizeConfigManager;
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.apply(validateCodeSecurityConfig)
				.and()
			.apply(smsCodeAuthenticationSecurityConfig)
				.and().
			formLogin()
				.loginPage("/authentication/require")
				.loginProcessingUrl("/authentication/form") // 登陆表单路径，要和页面表达路径一样
				.successHandler(loginSuccessHandler)
				.failureHandler(loginFailureHandler)
				.and()
			.authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
				.and()
			.csrf().disable();
		
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		
		authorizeConfigManager.config(http.authorizeRequests());
		
		http.addFilterBefore(corsControllerFilter, SecurityContextPersistenceFilter.class);
		
	}

}
