package com.lichi.increaselimit.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.common.utils.HuanXinUtils;
import com.lichi.increaselimit.common.utils.UserIdUtils;
import com.lichi.increaselimit.sys.controller.dto.SysUserRoleDto;
import com.lichi.increaselimit.sys.dao.SysUserDao;
import com.lichi.increaselimit.sys.dao.SysUserRoleDao;
import com.lichi.increaselimit.sys.entity.SysUser;
import com.lichi.increaselimit.sys.entity.SysUserRole;
import com.lichi.increaselimit.sys.service.SysUserService;

import tk.mybatis.mapper.entity.Example;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserMapper;
	@Autowired
	private SysUserRoleDao sysUserRoleMapper;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;

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

	/**
	 * 插入用户的时候要注册环信用户
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertUser(SysUser sysUser) {
		String userId = UserIdUtils.getUserId();
		sysUser.setId(userId);
		sysUser.setCreateTime(new Date());
		sysUser.setUpdateTime(new Date());
		sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
		SysUser user = sysUserMapper.loadUserInfoByMobile(sysUser.getMobile());
		if (user != null) {
			throw new BusinessException(ResultEnum.MOBILE_EXIST);
		}
		sysUserMapper.insertSelective(sysUser);

		try {
			HuanXinUtils.registerUser(userId, restTemplate);
		} catch (Exception e) {
			throw new BusinessException(ResultEnum.REGISTER_ERROR);
		}

	}

	@Override
	public PageInfo<SysUser> selectAll(Integer page, Integer size) {
		PageHelper.startPage(page, size);
		PageHelper.orderBy("create_time desc");
		List<SysUser> list = sysUserMapper.selectAll();
		PageInfo<SysUser> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public void deleteSysUser(String id) {
		sysUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updatePassword(SysUser sysUser) {
		SysUser user = sysUserMapper.loadUserInfoByMobile(sysUser.getMobile());
		if(user == null) {
			throw new BusinessException(ResultEnum.MOBILE_NUM_EMPTY);
		}
		user.setUpdateTime(new Date());
		user.setPassword(passwordEncoder.encode(sysUser.getPassword()));
		sysUserMapper.updateByPrimaryKey(user);
	}

	@Override
	public SysUser loadUserInfoByUsername(String username) {
		List<SysUser> list = selectByUsername(username);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	/**
	 * 通过用户名查找用户信息
	 * 
	 * @param username
	 * @return
	 */
	private List<SysUser> selectByUsername(String username) {
		Example example = new Example(SysUser.class);
		example.createCriteria().andEqualTo("username", username);
		List<SysUser> list = sysUserMapper.selectByExample(example);
		return list;
	}

	@Override
	public void updateSysUserDept(SysUser sysUser) {
		sysUser.setUpdateTime(new Date());
		sysUserMapper.updateByPrimaryKeySelective(sysUser);
	}

	@Override
	public void updateRole(List<SysUserRoleDto> list) {
		List<SysUserRole> resultlist = new ArrayList<>();
		list.stream().forEach( e -> {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserId(e.getId());
			sysUserRole.setRoleId(e.getRoleId());
			resultlist.add(sysUserRole);
		});
		sysUserRoleMapper.insertList(resultlist);
	}
	

}
