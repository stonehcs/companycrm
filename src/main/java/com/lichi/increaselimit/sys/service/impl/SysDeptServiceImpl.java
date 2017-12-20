package com.lichi.increaselimit.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.sys.dao.SysDeptDao;
import com.lichi.increaselimit.sys.entity.SysDept;
import com.lichi.increaselimit.sys.service.SysDeptService;

@Service
public class SysDeptServiceImpl implements SysDeptService{
	@Autowired
	private SysDeptDao deptDao;

	@Override
	public PageInfo<SysDept> selectAll(Integer page, Integer size) {
		PageHelper.startPage(page, size);
		PageHelper.orderBy("create_time desc");
		List<SysDept> list = deptDao.selectAll();
		PageInfo<SysDept> info = new PageInfo<>(list);
		return info;
	}

	@Override
	public SysDept selectOne(Integer id) {
		return deptDao.selectByPrimaryKey(id);
	}

	@Override
	public void insertOne(SysDept dept) {
		SysDept deptresult = deptDao.selectByName(dept.getDeptName());
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
	public void update(SysDept dept) {
		dept.setUpdateTime(new Date());
		deptDao.updateByPrimaryKeySelective(dept);
		
	}

	@Override
	public List<SysDept> selectList() {
		PageHelper.orderBy("create_time desc");
		List<SysDept> list = deptDao.selectAll();
		return list;
	}

}
