package com.lichi.increaselimit.user.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.common.utils.IdUtils;
import com.lichi.increaselimit.user.dao.SocialUserDao;
import com.lichi.increaselimit.user.dao.UserDao;
import com.lichi.increaselimit.user.entity.SocialUserInfo;
import com.lichi.increaselimit.user.entity.User;
import com.lichi.increaselimit.user.entity.UserVo;
import com.lichi.increaselimit.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userMapper;
	@Autowired
	private SocialUserDao socialUserDao;

	@Override
	public User loadUserInfoByMobile(String mobile) {
		User user = userMapper.loadUserInfoByMobile(mobile);
		return user;
	}
	
	@Override
	public User loadUserInfoByUserId(String userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		return user;
	}

	@Override
	public PageInfo<UserVo> selectAll(Integer page, Integer size) {
		PageHelper.startPage(page,size);
		PageHelper.orderBy("create_time desc");
		List<UserVo> list = userMapper.selectAllUser();
		list.forEach(e ->{
			String id = e.getId();
			List<SocialUserInfo> selectByUserId = socialUserDao.selectByUserId(id);
			selectByUserId.forEach(a ->{
				String providerId = a.getProviderId();
				String displayName = a.getDisplayName();
				if("weixin".equals(providerId)) {
					e.setWeixin(displayName);
				}
				if("qq".equals(providerId)) {
					e.setQq(displayName);
				}
				if("weibo".equals(providerId)) {
					e.setWeibo(displayName);
				}
			});
		});
		PageInfo<UserVo> pageInfo = new PageInfo<UserVo>(list);
		return pageInfo;
	}

	@Override
	public UserVo selectByPid(String pid) {
		return userMapper.selectByPid(pid);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public User insertCustomer(String mobile,String pid) {
		User byMobile = userMapper.loadUserInfoByMobile(mobile);
		if(byMobile != null) {
			throw new BusinessException(ResultEnum.MOBILE_EXIST);
		}
		String userId = IdUtils.getUserId();
		User user = new User();
		user.setId(userId);
		user.setUsername(mobile);
		user.setNickname(mobile);
		user.setMobile(mobile);
		user.setCreateTime(new Date());
		user.setVipLevel(1);
		user.setPid(pid);
		userMapper.insertSelective(user);
		userMapper.updatePidRank(pid);
		return user;
	}
}
