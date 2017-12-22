package com.lichi.increaselimit.sys.dao;

import java.util.List;

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

	@Select("select a.* from t_sys_role a,t_sys_user_role b where b.user_id = #{id}")
	List<SysRole> getUserRole(String id);
}