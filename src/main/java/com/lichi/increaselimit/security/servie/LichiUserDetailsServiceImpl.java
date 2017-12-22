package com.lichi.increaselimit.security.servie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.lichi.increaselimit.common.Constants;
import com.lichi.increaselimit.common.utils.RedisUtils;
import com.lichi.increaselimit.sys.entity.ResourceVo;
import com.lichi.increaselimit.sys.entity.SysButton;
import com.lichi.increaselimit.sys.entity.SysRole;
import com.lichi.increaselimit.sys.entity.SysRoleResource;
import com.lichi.increaselimit.sys.entity.SysUser;
import com.lichi.increaselimit.sys.service.SysButtonService;
import com.lichi.increaselimit.sys.service.SysRoleService;
import com.lichi.increaselimit.sys.service.SysUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 重写,通过用户名获取用户信息
 * 
 * @author majie
 *
 */
@Slf4j
@Component
public class LichiUserDetailsServiceImpl implements UserDetailsService,SocialUserDetailsService {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysButtonService sysButtonService;
	@Autowired
	private RedisUtils redisUtils;
	
	@Override
	public SysUser loadUserByUsername(String mobile) throws UsernameNotFoundException {

		log.info("手机号码为:" + mobile);
		SysUser user = sysUserService.loadUserInfoByMobile(mobile);
		if(user != null) {
			List<SysRole> userRoles = sysRoleService.getUserRole(user.getId());
			
			cacheResource(user.getId(), userRoles);
		}
		return user;
	}


	@Override
	public SysUser loadUserByUserId(String userId) throws UsernameNotFoundException {
		return sysUserService.loadUserInfoByUserId(userId);
	}

	/**
	 * 缓存资源信息
	 * @param userId
	 * @param userRole
	 */
	private void cacheResource(String userId, List<SysRole> userRole) {
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
	}
}
