package com.lichi.increaselimit.user.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * 系统用户
 * @author majie
 *
 */
@Table(name = "t_sys_user")
@Data
public class SysUser implements UserDetails{
	
	private static final long serialVersionUID = -2126752373347970007L;

	@Id
	private Integer id;
	
	private String username;
	
	private String mobile;
	
	private String password;
	
	private Date createTime;
	
	private Date updateTime;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
