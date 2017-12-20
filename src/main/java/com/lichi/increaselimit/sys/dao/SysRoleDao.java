package com.lichi.increaselimit.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lichi.increaselimit.common.mapper.BaseMapper;
import com.lichi.increaselimit.sys.entity.SysRole;

/**
 * RoleDao
 * @author majie
 *
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRole>{

	@Select("select * from t_sys_role where role_name = #{roleName}")
	SysRole selectByName(String roleName);
}
