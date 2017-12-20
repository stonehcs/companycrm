package com.lichi.increaselimit.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.sys.dao.RoleDao;
import com.lichi.increaselimit.sys.entity.Role;
import com.lichi.increaselimit.sys.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleDao roleDao;

	@Override
	public PageInfo<Role> selectAll(Integer page, Integer size) {
		PageHelper.startPage(page, size);
		PageHelper.orderBy("create_time desc");
		List<Role> list = roleDao.selectAll();
		PageInfo<Role> info = new PageInfo<>(list);
		return info;
	}

	@Override
	public Role selectOne(Integer id) {
		return roleDao.selectByPrimaryKey(id);
	}

	@Override
	public void insertOne(Role Role) {
		Role Roleresult = roleDao.selectByName(Role.getRoleName());
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
	public void update(Role Role) {
		Role.setUpdateTime(new Date());
		roleDao.updateByPrimaryKeySelective(Role);
		
	}

	@Override
	public List<Role> selectList() {
		PageHelper.orderBy("create_time desc");
		List<Role> list = roleDao.selectAll();
		return list;
	}

}
