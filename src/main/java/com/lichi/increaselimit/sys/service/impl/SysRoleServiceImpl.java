package com.lichi.increaselimit.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.sys.dao.SysRoleDao;
import com.lichi.increaselimit.sys.dao.SysRoleResourceDao;
import com.lichi.increaselimit.sys.entity.SysRole;
import com.lichi.increaselimit.sys.entity.SysRoleResource;
import com.lichi.increaselimit.sys.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService{
	@Autowired
	private SysRoleDao roleDao;
	@Autowired
	private SysRoleResourceDao sysRoleResourceDao;

	@Override
	public PageInfo<SysRole> selectAll(Integer page, Integer size) {
		PageHelper.startPage(page, size);
		PageHelper.orderBy("create_time desc");
		List<SysRole> list = roleDao.selectAll();
		PageInfo<SysRole> info = new PageInfo<>(list);
		return info;
	}

	@Override
	public SysRole selectOne(Integer id) {
		return roleDao.selectByPrimaryKey(id);
	}

	@Override
	public void insertOne(SysRole Role) {
		SysRole Roleresult = roleDao.selectByName(Role.getRoleName());
		if(Roleresult != null) {
			throw new BusinessException(ResultEnum.ROLE_EXIST);
		}
		Role.setUpdateTime(new Date());
		Role.setCreateTime(new Date());
		roleDao.insertSelective(Role);
	}

	@Override
	public void deleteOne(Integer id) {
		roleDao.deleteByPrimaryKey(id);
	}

	@Override
	public void update(SysRole Role) {
		Role.setUpdateTime(new Date());
		roleDao.updateByPrimaryKeySelective(Role);
		
	}

	@Override
	public List<SysRole> selectList() {
		PageHelper.orderBy("create_time desc");
		List<SysRole> list = roleDao.selectAll();
		return list;
	}

	@Override
	public void addResource(List<SysRoleResource> resultlist) {
		sysRoleResourceDao.insertList(resultlist);
	}

}
