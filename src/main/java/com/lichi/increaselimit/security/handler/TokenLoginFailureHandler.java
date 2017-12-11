package com.lichi.increaselimit.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lichi.increaselimit.common.utils.ResultVoUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 登录失败处理器
 * @author majie
 *
 */
@Component
@Slf4j
public class TokenLoginFailureHandler implements AccessDeniedHandler{

	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void handle(HttpServletRequest arg0, HttpServletResponse response, AccessDeniedException arg2)
			throws IOException, ServletException {
		log.info("登录认证失败");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(ResultVoUtil.error(1, "权限不足")));
		
	}

}