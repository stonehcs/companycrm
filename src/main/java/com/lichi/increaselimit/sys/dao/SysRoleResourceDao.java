package com.lichi.increaselimit.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lichi.increaselimit.common.mapper.BaseMapper;
import com.lichi.increaselimit.sys.entity.SysRoleResource;

/**
 * SysRoleResourceDao
 * 
 * @author majie
 *
 */
@Mapper
public interface SysRoleResourceDao extends BaseMapper<SysRoleResource> {

	@Select("select e.* from t_sys_user b,t_sys_role c,t_sys_user_role d,t_sys_role_resource e  " + 
			"where e.role_id = c.id " + 
			"and c.id = d.role_id " + 
			"and d.user_id = b.id "
			+ "and b.id = #{userId}")
	List<SysRoleResource> selectUserResource(String userId);

}
