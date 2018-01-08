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

	@Select("select e.*,m.menu_name,t.button_name  " + 
			"from t_sys_role_resource e  " + 
			"INNER JOIN t_sys_role c on e.role_id = c.id  " + 
			"INNER JOIN t_sys_user_role d on c.id = d.role_id  " + 
			"INNER JOIN t_sys_user b on d.user_id = b.id " + 
			"INNER JOIN t_sys_menu m on e.menu_id = m.id " + 
			"LEFT JOIN t_sys_button t on e.button_id = t.id " + 
			"where b.id = #{userId}")
	List<SysRoleResource> selectUserResource(String userId);

}
