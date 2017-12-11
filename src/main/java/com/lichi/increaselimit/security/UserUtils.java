package com.lichi.increaselimit.security;

import org.springframework.security.core.context.SecurityContextHolder;

import com.lichi.increaselimit.user.entity.SysUser;

/**
 * 获取当前用户信息
 * @author majie
 *
 */
public class UserUtils {
	
	/**
	 * 获取当前用户名
	 * @return
	 */
	public static SysUser getUserInfo() {
		return (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
