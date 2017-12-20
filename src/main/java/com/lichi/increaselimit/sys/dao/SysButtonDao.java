package com.lichi.increaselimit.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lichi.increaselimit.common.mapper.BaseMapper;
import com.lichi.increaselimit.sys.entity.SysButton;

/**
 * SysButtonDao
 * @author majie
 *
 */
@Mapper
public interface SysButtonDao extends BaseMapper<SysButton>{

	@Select("select * from t_sys_button where menu_id = #{id}")
	List<SysButton> selectByMenuId(Integer id);

}
