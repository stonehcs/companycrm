package com.lichi.increaselimit.course.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.course.dao.CourseMapper;
import com.lichi.increaselimit.course.entity.Course;
import com.lichi.increaselimit.course.entity.CourseVo;
import com.lichi.increaselimit.course.service.CourseService;

@Service
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseMapper courseMapper;

	@Override
	public PageInfo<CourseVo> getCourseList(Integer page, Integer size, Integer locationId) {
		PageHelper.startPage(page, size);
		List<CourseVo> list = courseMapper.selectByLocationId(locationId);
		list.stream().forEach(e -> {
			getPersons(e);
		});
		PageInfo<CourseVo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	@Override
	public PageInfo<CourseVo> getCourseList(Integer page, Integer size) {
		PageHelper.startPage(page, size);
		PageHelper.orderBy("start_time asc");
		List<CourseVo> list = courseMapper.selectList();
		list.stream().forEach(e -> {
			getPersons(e);
		});
		PageInfo<CourseVo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public CourseVo getCourse(Integer id) {
		CourseVo courseVo = courseMapper.selectCourseDetails(id);
		getPersons(courseVo);
		return courseVo;
	}

	@Override
	public void addCourse(Course course) {
		course.setCreateTime(new Date());
		course.setUpdateTime(new Date());
		if(course.getEndTime().getTime() < course.getStartTime().getTime()) {
			throw new BusinessException(ResultEnum.ENDTIME_BIGGER_THEN_STARTTIME);
		}
		courseMapper.insertSelective(course);
	}

	@Override
	public void deleteCourse(Integer id) {
		courseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateCourse(Course course) {
		course.setUpdateTime(new Date());
		courseMapper.updateByPrimaryKeySelective(course);
	}

	@Override
	public void updateCourseTimes(Integer id) {
		courseMapper.updateCourseTimes(id);
	}

	@Override
	public PageInfo<CourseVo> seleteByLike(Integer page, Integer size, String name) {
		PageHelper.startPage(page, size);
		PageHelper.orderBy("start_time asc");
		List<CourseVo> list = courseMapper.selectByLike(name);
		list.stream().forEach(e -> {
			getPersons(e);
		});
		PageInfo<CourseVo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	
	/**
	 * 获取报名人数
	 * @param vo
	 */
	private void getPersons(CourseVo vo) {
		Integer signpersons = courseMapper.getCount(vo.getId(), 0);
		vo.setSignUpPerson(signpersons);
		Integer paypersons = courseMapper.getCount(vo.getId(), 1);
		vo.setPayPerson(paypersons);
	}

}
