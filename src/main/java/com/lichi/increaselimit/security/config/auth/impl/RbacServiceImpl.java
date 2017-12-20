package com.lichi.increaselimit.security.config.auth.impl;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.lichi.increaselimit.common.utils.RedisUtils;
import com.lichi.increaselimit.security.config.auth.RbacService;

import lombok.extern.slf4j.Slf4j;

@Component("rbacService")
@Slf4j
public class RbacServiceImpl implements RbacService {

	@Autowired
	private RedisUtils redisUtils;

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		log.info("用户权限校验：" + request.getRequestURI());

		boolean hasPermission = false;

		String token = request.getHeader("token");

		/**
		 * 延长token
		 */
		if (!StringUtils.isBlank(token)) {
			log.info("用户名：" + token);
			
			boolean exists = redisUtils.exists("login_user:" + token);
			if (exists) {
				redisUtils.expire("login_user:" + token, 7200);
				hasPermission = true;
			}
		}

		// 这里应该去数据库读取用户信息，所对应的url放入下面set
		Set<String> urls = new HashSet<>();

		for (String url : urls) {
			if (antPathMatcher.match(url, request.getRequestURI())) {
				hasPermission = true;
				break;
			}
		}
		return true;
	}

}
