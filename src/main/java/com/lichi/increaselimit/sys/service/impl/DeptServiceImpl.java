package com.lichi.increaselimit.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.sys.dao.DeptDao;
import com.lichi.increaselimit.sys.entity.Dept;
import com.lichi.increaselimit.sys.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService{
	@Autowired
	private DeptDao deptDao;

	@Override
	public PageInfo<Dept> selectAll(Integer page, Integer size) {
		PageHelper.startPage(page, size);
		PageHelper.orderBy("create_time desc");
		List<Dept> list = deptDao.selectAll();
		PageInfo<Dept> info = new PageInfo<>(list);
		return info;
	}

	@Override
	public Dept selectOne(Integer id) {
		return deptDao.selectByPrimaryKey(id);
	}

	@Override
	public void insertOne(Dept dept) {
		Dept deptresult = deptDao.selectByName(dept.getDeptName());
		if(deptresult != null) {
			throw new BusinessException(ResultEnum.DEPT_EXIST);
		}
		dept.setUpdateTime(new Date());
		dept.setCreateTime(new Date());
		deptDao.insertSelective(dept);
	}

	@Override
	public void deleteOne(Integer id) {
		deptDao.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Dept dept) {
		dept.setUpdateTime(new Date());
		deptDao.updateByPrimaryKeySelective(dept);
		
	}

	@Override
	public List<Dept> selectList() {
		PageHelper.orderBy("create_time desc");
		List<Dept> list = deptDao.selectAll();
		return list;
	}

}
