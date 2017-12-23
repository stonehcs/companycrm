package com.lichi.increaselimit.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
public interface SysUserDao extends BaseMapper<SysUser> {

	/**
	 * 获取用户信息
	 * 
	 * @param username
	 * @return
	 */
	@Select("select a.*,b.dept_name from t_sys_user a left join t_sys_dept b on a.dept_id = b.id where mobile=#{mobile}")
	SysUserVo loadUserInfoByMobile(String mobile);
	
	List<SysUserVo> selectAllUser(@Param(value = "keys") String keys);

}
