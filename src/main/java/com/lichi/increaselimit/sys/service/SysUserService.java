package com.lichi.increaselimit.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.sys.controller.dto.SysUserRoleDto;
import com.lichi.increaselimit.sys.entity.SysUser;

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
	 * @return 
	 */
	void insertUser(SysUser sysUser);

	/**
	 * 获取所有用户信息
	 * @param size 
	 * @param page 
	 * @return
	 */
	PageInfo<SysUser> selectAll(Integer page, Integer size);

	/**
	 * 根据id删除用户信息
	 * @param id
	 */
	void deleteSysUser(String id);

	/**
	 * 更新用户信息
	 * @param sysUser
	 */
	void updatePassword(SysUser sysUser);

	/**
	 * 更新用户部门
	 * @param sysUser
	 */
	void updateSysUserDept(SysUser sysUser);

	/**
	 * 跟用户添加角色信息
	 * @param list
	 */
	void updateRole(List<SysUserRoleDto> list);
	
}
