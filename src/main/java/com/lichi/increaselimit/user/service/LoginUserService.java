package com.lichi.increaselimit.user.service;

import com.lichi.increaselimit.user.entity.LoginUser;

/**
 * 用户login service
 * @author majie
 *
 */
public interface LoginUserService {
	
	/**
	 * 新增一个登陆用户
	 * @param login
	 */
	void addLoginUser(LoginUser login);

	void deleteLoginUser(String id);
	
}
