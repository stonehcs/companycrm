package com.lichi.increaselimit.security.config.auth.impl;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.lichi.increaselimit.security.config.auth.RbacService;

import lombok.extern.slf4j.Slf4j;

@Component("rbacService")
@Slf4j
public class RbacServiceImpl implements RbacService{
	
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		log.info("用户权限校验：" + request.getRequestURI());
		Object principal = authentication.getPrincipal();
		
		//这里暂时这么写 TODO:
		boolean hasPermission = true;
		if(principal instanceof UserDetails) {
			String username = ((UserDetails)principal).getUsername();
			log.info("用户名：" + username);
			//这里应该去数据库读取用户信息，所对应的url放入下面set
			Set<String> urls = new HashSet<>();
			
			for(String url : urls) {
				if(antPathMatcher.match(url, request.getRequestURI())) {
					hasPermission = true;
					break;
				}
			}
		}
		return hasPermission;
	}

}
