package com.lichi.increaselimit.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lichi.increaselimit.common.mapper.BaseMapper;
import com.lichi.increaselimit.course.entity.Teacher;
import com.lichi.increaselimit.course.entity.TeacherVo;

/**
 * TeacherMapper
 * 
 * @author majie
 *
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

	@Select("select t.id,t.teachername from t_teacher t")
	List<TeacherVo> selectAllTeacher();

}