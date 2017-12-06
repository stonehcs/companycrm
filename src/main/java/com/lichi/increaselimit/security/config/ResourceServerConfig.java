package com.lichi.increaselimit.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.cors.CorsUtils;

import com.alibaba.fastjson.JSONObject;
import com.lichi.increaselimit.common.config.CorsControllerFilter;
import com.lichi.increaselimit.common.vo.ResultVo;
import com.lichi.increaselimit.security.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
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
@EnableResourceServer
// @CrossOrigin
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

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

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/authentication/require") // 登陆校验权限，controller路径
				.loginProcessingUrl("/authentication/form") // 登陆表单路径，要和页面表达路径一样
				.successHandler(loginSuccessHandler).failureHandler(loginFailureHandler).and().authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()// 解决preflight跨域
				.antMatchers("/login.html", "/authentication/require", "/captcha-image").permitAll().anyRequest()
				.authenticated().and().csrf().disable();

		http.apply(validateCodeSecurityConfig).and().apply(smsCodeAuthenticationSecurityConfig).and()
				.authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/v2/**", "/swagger**", "/druid/**", "/swagger-resources/**", "/oauth2/client",
						"/authentication/mobile", "/code/**", "/sysuser/regiter")
				.permitAll().anyRequest().authenticated().and().csrf().disable();

		http.addFilterBefore(corsControllerFilter, SecurityContextPersistenceFilter.class);
		
	}

//	@Bean  
//    public WebResponseExceptionTranslator webResponseExceptionTranslator() {  
//        return new DefaultWebResponseExceptionTranslator() {  
//            @Override  
//            public ResponseEntity translate(Exception e) throws Exception {  
//                ResponseEntity responseEntity = super.translate(e);  
//                OAuth2Exception body = (OAuth2Exception) responseEntity.getBody();  
//                HttpHeaders headers = new HttpHeaders();  
//                headers.setAll(responseEntity.getHeaders().toSingleValueMap());  
//                // do something with header or response  
//                if(401==responseEntity.getStatusCode().value()){      
//                    ResultVo r = new ResultVo<>(); 
//                    r.setCode(401);
//                    r.setMsg( "Invalid access token");
//                    return new ResponseEntity(JSONObject.toJSON(r), headers, responseEntity.getStatusCode());  
//                }else{  
//                    return new ResponseEntity(body, headers, responseEntity.getStatusCode());  
//                }  
//                  
//            }  
//        };
//	}
}
