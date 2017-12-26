package com.lichi.increaselimit.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lichi.increaselimit.common.mapper.BaseMapper;
import com.lichi.increaselimit.sys.entity.SysMenu;

/**
 * SysMuneDao
 * @author majie
 *
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenu>{

	/**
	 * 根据名称查询
	 * @param menuName
	 * @return
	 */
	@Select("select * from t_sys_menu where menu_name = #{menuName}")
	SysMenu selectByName(String menuName);

	/**
	 * 根据主键查询子节点
	 * @param id
	 * @return
	 */
	@Select("select * from t_sys_menu where pid = #{id}")
	List<SysMenu> selectChild(Integer id);

	/**
	 * 根据parent查询父节点
	 * @param parent
	 * @return
	 */
	@Select("select * from t_sys_menu where pid = -1")
	SysMenu selectRoot();

	@Select("select a.* from t_sys_menu a,t_sys_user b,t_sys_role c,t_sys_user_role d,t_sys_role_resource e " + 
			"where b.id = d.user_id " + 
			"and d.role_id = c.id " + 
			"and c.id = e.role_id " + 
			"and e.menu_id = a.id and b.id = #{userId}")
	List<SysMenu> selectByUserId(String userId);
	

}
