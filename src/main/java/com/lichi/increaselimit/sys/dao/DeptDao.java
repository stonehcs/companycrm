package com.lichi.increaselimit.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lichi.increaselimit.common.mapper.BaseMapper;
import com.lichi.increaselimit.sys.entity.Dept;

/**
 * DeptDao
 * @author majie
 *
 */
@Mapper
public interface DeptDao extends BaseMapper<Dept>{

	@Select("select * from t_sys_dept where dept_name = #{deptName}")
	Dept selectByName(String deptName);
}
