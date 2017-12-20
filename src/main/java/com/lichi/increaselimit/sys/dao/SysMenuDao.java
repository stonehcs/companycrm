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
	

}
