package com.lichi.increaselimit.user.service;

import com.lichi.increaselimit.user.entity.User;

/**
 * 用户service
 * @author majie
 *
 */
public interface UserService {
	
	/**
	 * 获取用户信息
	 * @param username
	 * @return
	 */
	User loadUserInfo(String username);
}
