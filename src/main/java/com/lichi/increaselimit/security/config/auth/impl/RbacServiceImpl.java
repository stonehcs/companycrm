package com.lichi.increaselimit.security.config.auth.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lichi.increaselimit.common.Constants;
import com.lichi.increaselimit.common.utils.RedisUtils;
import com.lichi.increaselimit.security.config.auth.RbacService;
import com.lichi.increaselimit.sys.entity.ResourceVo;
import com.lichi.increaselimit.sys.entity.SysRole;
import com.lichi.increaselimit.sys.entity.SysUser;
import com.lichi.increaselimit.sys.service.SysRoleService;
import com.lichi.increaselimit.sys.service.SysUserService;

import lombok.extern.slf4j.Slf4j;

@Component("rbacService")
@Slf4j
public class RbacServiceImpl implements RbacService {

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserService sysUserService;

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

		String token = request.getHeader("token");

		//websocket的token
		if(request.getRequestURI().startsWith("/websocket")) {
			token = request.getParameter("token");
		}
		
		log.info("用户资源权限校验,请求资源:{},token:{}", request.getRequestURI(), token);

		boolean hasPermission = false;

		if (StringUtils.isNotBlank(token)) {

			boolean exists = redisUtils.exists(Constants.LOGIN_SYS_USER + token);
			if (exists) {
				redisUtils.expire(Constants.LOGIN_SYS_USER + token, 7200);
			}else {
				return hasPermission;
			}
		} else {
			return hasPermission;
		}
		
		//管理员有所有权限
		String userStr = redisUtils.get(Constants.LOGIN_SYS_USER + token);
		SysUser sysUser = JSONObject.parseObject(userStr, SysUser.class);
		List<SysRole> userRole = sysRoleService.getUserRole(sysUser.getId());
		boolean filter = userRole.stream().anyMatch(e -> StringUtils.equalsIgnoreCase("admin", e.getRoleName()));

		if(filter) {
			return true;
		}
		
		// 从缓存中获取权限,缓存为空就去数据库获取然后更新到缓存
		String resourceStr = redisUtils.get(Constants.RESOURCE + token);

		if (StringUtils.isNotBlank(resourceStr)) {
			
			JSONArray parseArray = JSON.parseArray(resourceStr);
//			ResourceVo resources = new ResourceVo("/button/{id}", "delete");
//			parseArray.add(resources);
			for (Object resource : parseArray) {
				ResourceVo resourceVo = (ResourceVo) resource;
				if (antPathMatcher.match(resourceVo.getUrl(), request.getRequestURI())
						&& StringUtils.equalsIgnoreCase(resourceVo.getMethod(), request.getMethod())) {
					hasPermission = true;
					break;
				}
			}
		} else {
			//从数据库获取
			List<ResourceVo> userResource = sysUserService.getUserResource(sysUser.getId());
			for (ResourceVo resource : userResource) {
				if (antPathMatcher.match(resource.getUrl(), request.getRequestURI())
						&& StringUtils.equalsIgnoreCase(resource.getMethod(), request.getMethod())) {
					hasPermission = true;
					break;
				}
			}
			redisUtils.set(Constants.RESOURCE + sysUser.getId(), JSONObject.toJSONString(userResource), 7200);
		}
		hasPermission = true; // 暂时先放过带有token的请求
		return hasPermission;
	}

}
