package com.lichi.increaselimit.course.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lichi.increaselimit.common.mapper.BaseMapper;
import com.lichi.increaselimit.course.entity.Course;
import com.lichi.increaselimit.course.entity.CourseVo;

/**
 * Course dao
 * @author majie
 *
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course>{
	
	@Update("update t_course set times = times + 1")
	void updateCourseTimes();

	/**
	 * 根据课程id查询课程详情 
	 * @param id
	 * @return
	 */
	@Select("select c.*,t.teachername,t.introduce from t_course c,t_teacher t where c.id = #{id} and c.teacher_id = t.id")
	CourseVo selectCourseDetails(Integer id);
}
