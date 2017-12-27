package com.lichi.increaselimit.user.service;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.user.entity.User;
import com.lichi.increaselimit.user.entity.UserVo;

/**
 * 用户service
 * @author majie
 *
 */
public interface UserService {
	
	/**
	 * 通过手机号获取用户信息
	 * @param username
	 * @return
	 */
	User loadUserInfoByMobile(String mobile);

	/**
	 * 通过用户id获取用户信息
	 * @param userId
	 * @return
	 */
	User loadUserInfoByUserId(String userId);
	
	/**
	 * 分页查询所有用户根据时间倒序
	 * @param page
	 * @param sizeInteger
	 * @return
	 */
	PageInfo<UserVo> selectAll(Integer page, Integer size);

	/**
	 * 查询上层关系
	 * @param pid
	 * @return
	 */
	UserVo selectByPid(String pid);
}
