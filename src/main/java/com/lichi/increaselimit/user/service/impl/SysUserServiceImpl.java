package com.lichi.increaselimit.user.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.user.dao.SysUserMapper;
import com.lichi.increaselimit.user.entity.SysUser;
import com.lichi.increaselimit.user.service.SysUserService;
import tk.mybatis.mapper.entity.Example;

@Service
public class SysUserServiceImpl implements SysUserService{
	
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public SysUser loadUserInfoByMobile(String mobile) {
		SysUser user = sysUserMapper.loadUserInfoByMobile(mobile);
		return user;
	}
	
	@Override
	public SysUser loadUserInfoByUserId(String userId) {
		SysUser user = sysUserMapper.selectByPrimaryKey(userId);
		return user;
	}

	@Override
	public void insertUser(SysUser sysUser) {
		sysUser.setCreateTime(new Date());
		SysUser user = sysUserMapper.loadUserInfoByMobile(sysUser.getMobile());
		if(user != null) {
			throw new BusinessException(ResultEnum.MOBILE_EXIST);
		}
		sysUserMapper.insert(sysUser);
	}

	@Override
	public List<SysUser> selectAll(Integer page, Integer size) {
		PageHelper.startPage(page,size);
		PageHelper.orderBy("create_time desc");
		List<SysUser> list = sysUserMapper.selectAll();
		return list;
	}

	@Override
	public void deleteSysUser(Integer id) {
		sysUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateSysUser(SysUser sysUser) {
		sysUser.setUpdateTime(new Date());
		sysUserMapper.updateByPrimaryKeySelective(sysUser);
	}

	@Override
	public SysUser loadUserInfoByUsername(String username) {
		List<SysUser> list = selectByUsername(username);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	/**
	 * 通过用户名查找用户信息
	 * @param username
	 * @return
	 */
	private List<SysUser> selectByUsername(String username) {
		Example example = new Example(SysUser.class);
		example.createCriteria().andEqualTo("username",username);
		List<SysUser> list = sysUserMapper.selectByExample(example);
		return list;
	}

}
