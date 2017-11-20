package com.lichi.increaselimit.security.servie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 重写,通过用户名获取用户信息
 * @author majie
 *
 */
@Slf4j
@Component 
public class LichiUserDetailsService implements UserDetailsService{

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("表单登录用户名:" + username);
		
		//TODO:
		
		//必须要有用户角色才能访问oauth2
		return new User(username, passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
	}


}
