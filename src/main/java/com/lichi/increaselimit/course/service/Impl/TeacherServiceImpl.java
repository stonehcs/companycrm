package com.lichi.increaselimit.course.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.lichi.increaselimit.course.dao.TeacherMapper;
import com.lichi.increaselimit.course.entity.Teacher;
import com.lichi.increaselimit.course.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherMapper mapper;
	
	@Override
	public List<Teacher> getTeacherList(Integer page, Integer size) {
		PageHelper.startPage(page,size);
		return mapper.selectAll();
	}

	@Override
	public Teacher getTeacher(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void addTeacher(Teacher teacher) {
		mapper.insertSelective(teacher);
	}

	@Override
	public void deleteTeacher(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		mapper.updateByPrimaryKeySelective(teacher);
	}

}
