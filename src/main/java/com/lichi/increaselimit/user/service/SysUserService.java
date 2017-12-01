package com.lichi.increaselimit.user.service;

import java.util.List;

import com.lichi.increaselimit.user.entity.SysUser;

/**
 * 系统用户service
 * @author majie
 *
 */
public interface SysUserService {
	
	/**
	 * 通过手机号获取用户信息
	 * @param username
	 * @return
	 */
	SysUser loadUserInfoByMobile(String mobile);
	
	/**
	 * 通过手机名获取用户信息
	 * @param username
	 * @return
	 */
	SysUser loadUserInfoByUsername(String username);

	/**
	 * 通过用户id获取用户信息
	 * @param userId
	 * @return
	 */
	SysUser loadUserInfoByUserId(String userId);
	
	/**
	 * 新增用户
	 * @param sysUser
	 */
	void insertUser(SysUser sysUser);

	/**
	 * 获取所有用户信息
	 * @param size 
	 * @param page 
	 * @return
	 */
	List<SysUser> selectAll(Integer page, Integer size);

	/**
	 * 根据id删除用户信息
	 * @param id
	 */
	void deleteSysUser(Integer id);

	/**
	 * 更新用户信息
	 * @param sysUser
	 */
	void updateSysUser(SysUser sysUser);
	
}
