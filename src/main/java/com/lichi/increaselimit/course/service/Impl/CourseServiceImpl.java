package com.lichi.increaselimit.course.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.course.dao.CourseDao;
import com.lichi.increaselimit.course.entity.Course;
import com.lichi.increaselimit.course.entity.CourseVo;
import com.lichi.increaselimit.course.service.CourseService;
import com.lichi.increaselimit.sys.entity.SysMessage;
import com.lichi.increaselimit.sys.service.SysMessageService;

@Service
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseMapper;
	@Autowired
	private SysMessageService sysMessageService;

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

	/**
	 * 添加课程以后发送通知
	 */
	@Override
	public void addCourse(Course course) {
		course.setCreateTime(new Date());
		course.setUpdateTime(new Date());
		if(course.getEndTime().getTime() < course.getStartTime().getTime()) {
			throw new BusinessException(ResultEnum.ENDTIME_BIGGER_THEN_STARTTIME);
		}
		courseMapper.insertSelective(course);
		
		SysMessage message = new SysMessage();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		message.setContent( "课程标题:" + course.getTitle() + ",开课时间:" + sdf.format(course.getStartTime()));
		message.setDescription("有新的课程开课了");
		sysMessageService.insertOne(message);
		
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
