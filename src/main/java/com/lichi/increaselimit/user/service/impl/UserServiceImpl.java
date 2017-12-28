package com.lichi.increaselimit.user.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.lichi.increaselimit.user.dao.VipLevelDao;
import com.lichi.increaselimit.user.entity.SocialUserInfo;
import com.lichi.increaselimit.user.entity.User;
import com.lichi.increaselimit.user.entity.UserShare;
import com.lichi.increaselimit.user.entity.UserVo;
import com.lichi.increaselimit.user.entity.VipLevel;
import com.lichi.increaselimit.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userMapper;
	@Autowired
	private SocialUserDao socialUserDao;
	@Autowired
	private VipLevelDao vipLevelDao;

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
	public PageInfo<UserVo> selectAll(Integer page, Integer size,String key) {
		PageHelper.startPage(page,size);
		PageHelper.orderBy("create_time desc");
		List<UserVo> list = null;
		if(StringUtils.isBlank(key)) {
			list = userMapper.selectAllUser();
		}else {
			list = userMapper.selectAllLike(key);
		}
		PageInfo<UserVo> pageInfo = getThirdInfo(list);
		return pageInfo;
	}

	@Override
	public UserVo selectByPid(String pid) {
		UserVo vo = userMapper.selectByPid(pid);
		Integer count = userMapper.selectShareCount(pid);
		List<UserShare> shares = userMapper.selectShareUser(pid);
		vo.setShares(shares);
		vo.setCount(count);
		return vo;
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

	@Override
	public void updateUserInfo(String userId, Integer level) {
		User record = new User();
		record.setId(userId);
		record.setVipLevel(level);
		record.setUpdateTime(new Date());
		userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<VipLevel> selectLevel() {
		return vipLevelDao.selectAll();
	}

	@Override
	public PageInfo<UserVo> getAllShare(Integer page, Integer size, String userId) {
		PageHelper.startPage(page,size);
		PageHelper.orderBy("create_time desc");
		List<UserVo> list = userMapper.getAllShare(userId);
		list.forEach(e -> {
			List<UserShare> shares = userMapper.selectShareUser(e.getId());
			e.setShares(shares);
		});
		PageInfo<UserVo> pageInfo = getThirdInfo(list);
		return pageInfo;
	}

	private PageInfo<UserVo> getThirdInfo(List<UserVo> list) {
		list.forEach(e ->{
			String id = e.getId();
			Integer count = userMapper.selectShareCount(id);
			e.setCount(count);
			
			List<UserShare> shares = userMapper.selectShareUser(id);
			
			e.setShares(shares);
			
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
}
