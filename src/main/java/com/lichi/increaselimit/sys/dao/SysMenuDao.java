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

	@Select("select * from t_sys_menu where menu_name = #{menuName}")
	SysMenu selectByName(String menuName);

	@Select("select * from t_sys_menu where parent = #{id}")
	List<SysMenu> selectChild(Integer id);

}
