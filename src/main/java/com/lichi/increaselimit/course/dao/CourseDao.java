package com.lichi.increaselimit.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
public interface CourseDao extends BaseMapper<Course>{
	
	@Update("update t_course set times = times + 1 where id = #{id}")
	void updateCourseTimes(Integer id);

	/**
	 * 根据课程id查询课程详情 
	 * @param id
	 * @return
	 */
	@Select("select a.*,b.teachername,b.introduce,b.img_url from t_course a left join t_teacher b on a.teacher_id = b.id where a.id=#{id}")
	CourseVo selectCourseDetails(Integer id);
	
	/**
	 * 模糊查询
	 * @param name
	 * @return 
	 */
	@Select("select a.*,b.teachername,b.introduce,b.img_url from t_course a left join t_teacher b on a.teacher_id = b.id "
			+ "where (a.title LIKE concat('%', #{name}, '%') or b.teachername LIKE concat('%', #{name}, '%') )")
	List<CourseVo> selectByLike(@Param(value = "name") String name);

	/**
	 * 获得对应人数
	 * @param id
	 * @param status
	 * @return
	 */
	@Select("select count(course_id) from t_user_course where course_id = #{id} and status = #{status}")
	Integer getCount(@Param(value = "id") Integer id, @Param(value = "status") Integer status);

	@Select("select a.*,b.teachername,b.introduce,b.img_url from t_course a left join t_teacher b on a.teacher_id = b.id where end_time > now()")
	List<CourseVo> selectList();
	
	@Select("select a.*,b.teachername,b.introduce,b.img_url from t_course a left join t_teacher b on a.teacher_id = b.id where a.location_id=#{locationId} and end_time > now()")
	List<CourseVo> selectByLocationId(Integer locationId);
}
