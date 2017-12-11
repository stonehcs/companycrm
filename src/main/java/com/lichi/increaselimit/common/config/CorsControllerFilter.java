package com.lichi.increaselimit.common.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSONObject;
import com.lichi.increaselimit.common.utils.RedisUtils;
import com.lichi.increaselimit.common.utils.ResultVoUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 跨域过滤器
 *
 */
@Component
@Slf4j
public class CorsControllerFilter extends OncePerRequestFilter implements InitializingBean{
	
	@Autowired
	private RedisUtils redisUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.setContentType("text/html;charset=UTF-8");
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE ,PUT");
		res.setHeader("Access-Control-Max-Age", "30");
		res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since,"
				+ " Pragma, Last-Modified, Cache-Control, Expires, Content-Type, "
				+ "X-E4M-With,userId,token,Authorization,deviceId,Access-Control-Allow-Origin,Access-Control-Allow-Headers,Access-Control-Allow-Methods");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("XDomainRequestAllowed", "1");
		
		refreshToken(request, response);
		
		chain.doFilter(request, response);
		
	}
	
	private void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getHeader("token");
		log.info("登录用户:" + username);
		if (!StringUtils.isBlank(username)) {
			String tokenJson = redisUtils.get("login_token:" + username);
			if (StringUtils.isBlank(tokenJson)) {
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().write(JSONObject
						.toJSONString(ResultVoUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "token失效")));
				return;
			}
			// 将生成的token放入redis
			redisUtils.set("login_token:" + username, tokenJson, 7200);
			
		}
	}

}