package com.lichi.increaselimit.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.common.utils.HuanXinUtils;
import com.lichi.increaselimit.common.utils.IdUtils;
import com.lichi.increaselimit.sys.dao.SysUserDao;
import com.lichi.increaselimit.sys.dao.SysUserRoleDao;
import com.lichi.increaselimit.sys.entity.SysUser;
import com.lichi.increaselimit.sys.entity.SysUserRole;
import com.lichi.increaselimit.sys.entity.SysUserVo;
import com.lichi.increaselimit.sys.service.SysUserService;
import com.lichi.increaselimit.user.dao.UserMapper;

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
		String userId = IdUtils.getUserId();
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

	@SuppressWarnings("deprecation")
	@Override
	public PageInfo<SysUserVo> selectAll(Integer page, Integer size, String key) {
		Map<String, Object> map = new HashMap<>();
		map.put("page", page - 1);
		map.put("size", size);
		map.put("keys", key);
		List<SysUserVo> list = sysUserMapper.selectAllUser(map);
		Integer countAll = sysUserMapper.countAll();
		PageInfo<SysUserVo> pageInfo = new PageInfo<SysUserVo>(list,countAll/size);
		pageInfo.setLastPage(countAll/size + 1);
		pageInfo.setTotal(countAll);
		return pageInfo;
	}

	@Override
	public void deleteSysUser(List<String> ids) {
		Example example = new Example(UserMapper.class);
		example.createCriteria().andIn("id", ids);
		sysUserMapper.deleteByExample(example);
	}

	@Override
	public void updatePassword(SysUser sysUser) {
		SysUser user = sysUserMapper.loadUserInfoByMobile(sysUser.getMobile());
		if (user == null) {
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
	@Transactional(rollbackFor = Exception.class)
	public void updateSysUserInfo(SysUser sysUser, List<Integer> roleIds) {
		sysUser.setUpdateTime(new Date());
		sysUserMapper.updateByPrimaryKeySelective(sysUser);
		
		if(roleIds != null && roleIds.size() > 0) {
			
			Example example = new Example(SysUserRole.class);
			example.createCriteria().andEqualTo("userId",sysUser.getUserId());
			sysUserRoleMapper.deleteByExample(example);
			
			List<SysUserRole> list = new ArrayList<>();
			roleIds.forEach( e -> {
				SysUserRole record = new SysUserRole();
				record.setUserId(sysUser.getId());
				record.setRoleId(e);
				list.add(record);
			});
			
			sysUserRoleMapper.insertList(list);
		}
	}

}
