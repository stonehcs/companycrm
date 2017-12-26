package com.lichi.increaselimit.security.servie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import com.lichi.increaselimit.sys.entity.SysUser;
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
	
	@Override
	public SysUser loadUserByUsername(String mobile) throws UsernameNotFoundException {

		log.info("手机号码为:" + mobile);
		SysUser user = sysUserService.loadUserInfoByMobile(mobile);
		if(user != null) {
			//缓存资源
			sysUserService.getUserResource(user.getId());
			
		}
		return user;
	}


	@Override
	public SysUser loadUserByUserId(String userId) throws UsernameNotFoundException {
		return sysUserService.loadUserInfoByUserId(userId);
	}


}
