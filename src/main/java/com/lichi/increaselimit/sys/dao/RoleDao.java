package com.lichi.increaselimit.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lichi.increaselimit.common.mapper.BaseMapper;
import com.lichi.increaselimit.sys.entity.Role;

/**
 * RoleDao
 * @author majie
 *
 */
@Mapper
public interface RoleDao extends BaseMapper<Role>{

	@Select("select * from t_sys_role where role_name = #{roleName}")
	Role selectByName(String roleName);
}
