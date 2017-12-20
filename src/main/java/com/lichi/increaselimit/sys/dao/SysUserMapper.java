package com.lichi.increaselimit.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lichi.increaselimit.common.mapper.BaseMapper;
import com.lichi.increaselimit.sys.entity.SysUser;
import com.lichi.increaselimit.sys.entity.SysUserVo;

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
	@Select("select a.*,b.dept_name from t_sys_user a,t_sys_dept b where a.dept_id = b.id and mobile=#{mobile}")
	SysUserVo loadUserInfoByMobile(String mobile);

}
