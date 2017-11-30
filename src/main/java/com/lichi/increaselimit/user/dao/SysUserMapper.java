package com.lichi.increaselimit.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lichi.increaselimit.common.mapper.BaseMapper;
import com.lichi.increaselimit.user.entity.SysUser;

/**
 * userdao
 * 
 * @author majie
 *
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

	/**
	 * 获取用户信息
	 * 
	 * @param username
	 * @return
	 */
	@Select("select * from t_sys_user where mobile=#{mobile}")
	SysUser loadUserInfoByMobile(String mobile);
}
