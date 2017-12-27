package com.lichi.increaselimit.user.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.user.entity.User;
import com.lichi.increaselimit.user.entity.UserVo;
import com.lichi.increaselimit.user.entity.VipLevel;

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
	
	/**
	 * 插入一个顾客
	 * @param string 
	 * @param socialUserInfo
	 * @return 
	 */
	User insertCustomer(String mobile, String string);

	/**
	 * 修改用户等级信息
	 * @param userId
	 * @param level
	 */
	void updateUserInfo(String userId, Integer level);

	/**
	 * 查询等级列表
	 * @return
	 */
	List<VipLevel> selectLevel();
	
	
}
