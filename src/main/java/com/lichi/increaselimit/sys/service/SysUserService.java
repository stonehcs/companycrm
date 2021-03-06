package com.lichi.increaselimit.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.sys.entity.ResourceVo;
import com.lichi.increaselimit.sys.entity.SysUser;
import com.lichi.increaselimit.sys.entity.SysUserVo;

/**
 * 系统用户service
 * @author majie
 *
 */
public interface SysUserService {
	
	/**
	 * 通过手机号获取用户信息
	 * @param mobile
	 * @return
	 */
	SysUser loadUserInfoByMobile(String mobile);
	
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
	PageInfo<SysUserVo> selectAll(Integer page, Integer size,String key);

	/**
	 * 根据id删除用户信息
	 * @param ids
	 */
	void deleteSysUser(List<String> ids);

	/**
	 * 更新用户信息密码
	 * @param sysUser
	 */
	void updatePassword(SysUser sysUser);

	/**
	 * 更新用户信息
	 * @param sysUser
	 * @param roleIds 
	 * @param token 
	 */
	void updateSysUserInfo(SysUser sysUser, List<Integer> roleIds, String token);
	
	/**
	 * 获取当前用户对应的resource
	 */
	List<ResourceVo> getUserResource(String userId);

	void grant(List<String> ids, Integer integer);
}
