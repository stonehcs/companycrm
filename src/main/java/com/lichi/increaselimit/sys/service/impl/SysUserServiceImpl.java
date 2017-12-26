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

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.Constants;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.common.utils.HuanXinUtils;
import com.lichi.increaselimit.common.utils.IdUtils;
import com.lichi.increaselimit.common.utils.RedisUtils;
import com.lichi.increaselimit.sys.dao.SysUserDao;
import com.lichi.increaselimit.sys.dao.SysUserRoleDao;
import com.lichi.increaselimit.sys.entity.ResourceVo;
import com.lichi.increaselimit.sys.entity.SysButton;
import com.lichi.increaselimit.sys.entity.SysRole;
import com.lichi.increaselimit.sys.entity.SysRoleResource;
import com.lichi.increaselimit.sys.entity.SysUser;
import com.lichi.increaselimit.sys.entity.SysUserRole;
import com.lichi.increaselimit.sys.entity.SysUserVo;
import com.lichi.increaselimit.sys.service.SysButtonService;
import com.lichi.increaselimit.sys.service.SysRoleService;
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
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysButtonService sysButtonService;

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
		Integer countAll = sysUserMapper.countAll();
		page = page <= 0 ? 1 : page;
		page = page > countAll/size + 1 ? countAll/size + 1 : page;
		
		map.put("start", (page-1)*size);
		map.put("end", page*size - (page-1)*size);
		map.put("keys", key);
		List<SysUserVo> list = sysUserMapper.selectAllUser(map);
		PageInfo<SysUserVo> pageInfo = new PageInfo<SysUserVo>(list,countAll/size);
		pageInfo.setLastPage(countAll/size + 1);
		pageInfo.setTotal(countAll);
		return pageInfo;
	}

	@Override
	public void deleteSysUser(List<String> ids) {
		Example example = new Example(SysUser.class);
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
	public void updateSysUserInfo(SysUser sysUser, List<Integer> roleIds, String token) {
		sysUser.setUpdateTime(new Date());
		sysUserMapper.updateByPrimaryKeySelective(sysUser);
		sysUserMapper.selectByPrimaryKey(sysUser.getId());
		redisUtils.del(Constants.LOGIN_SYS_USER+token);
		redisUtils.set(Constants.LOGIN_SYS_USER+token, JSONObject.toJSONString(sysUser), 7200);
		Example example = new Example(SysUserRole.class);
		example.createCriteria().andEqualTo("userId",sysUser.getId());
		sysUserRoleMapper.deleteByExample(example);
		
		if(roleIds != null && roleIds.size() > 0) {
			
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

	@Override
	public List<ResourceVo> getUserResource(String userId) {
		List<SysRole> userRoles = sysRoleService.getUserRole(userId);
		List<ResourceVo> cacheResource = cacheResource(userId, userRoles);
		return cacheResource;
	}

	
	/**
	 * 缓存资源信息
	 * @param userId
	 * @param userRole
	 */
	private List<ResourceVo> cacheResource(String userId, List<SysRole> userRole) {
		List<ResourceVo> list = new ArrayList<>();
		
		/**
		 * 登录时候取出用户资源放入缓存
		 */
		userRole.forEach(e ->{
			List<SysRoleResource> resources = sysRoleService.selectResource(e.getId());
			resources.forEach(a -> {
				Integer type = a.getType();
				if(type == 1) {
					Integer buttonId = a.getButtonId();
					SysButton button = sysButtonService.selectOne(buttonId);
					ResourceVo resourceVo = new ResourceVo(button.getUrl(), button.getMethod());
					list.add(resourceVo);
				}
				
			});
		});
		redisUtils.set(Constants.RESOURCE + userId, JSONObject.toJSONString(list), 7200);
		
		return list;
	}
}
