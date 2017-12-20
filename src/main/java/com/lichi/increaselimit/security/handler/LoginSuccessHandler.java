package com.lichi.increaselimit.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.lichi.increaselimit.common.utils.RedisUtils;
import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.security.UserUtils;
import com.lichi.increaselimit.sys.entity.SysUser;

import lombok.extern.slf4j.Slf4j;

/**
 * 登录成功处理
 * 创建oauth2.0token
 * @author majie
 *
 */
@Component("loginSuccessHandler")
@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Autowired
	private RedisUtils redisUtils;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		log.info("登录成功");

		String username = ((SysUser) authentication.getPrincipal()).getUsername();
		
		//将用户信息放入redis
		redisUtils.set("login_user:" + username,JSONObject.toJSONString(UserUtils.getUserInfo()),7200);
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(JSONObject.toJSONString(ResultVoUtil.success(UserUtils.getUserInfo())));
	}

}