package com.lichi.increaselimit.security.config.auth.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lichi.increaselimit.common.utils.RedisUtils;
import com.lichi.increaselimit.security.config.auth.RbacService;
import com.lichi.increaselimit.sys.entity.ResourceVo;

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

		if (!StringUtils.isBlank(token)) {
			log.info("用户名：" + token);

			boolean exists = redisUtils.exists("login_user:" + token);
			if (exists) {
				redisUtils.expire("login_user:" + token, 7200);
				hasPermission = true;
			}
		}
		
		//从缓存中获取权限
		String string = redisUtils.get("resource:" + token);

		if (StringUtils.isNoneBlank(string)) {

			JSONArray parseArray = JSON.parseArray(string);

			for (Object resource : parseArray) {
				ResourceVo resourceVo = (ResourceVo) resource;
				if (antPathMatcher.match(resourceVo.getUrl(), request.getRequestURI())
						&& antPathMatcher.match(resourceVo.getMethod(), request.getMethod())) {
					hasPermission = true;
					break;
				}
			}
		}
		return true;
	}

}
