package com.lichi.increaselimit.sys.entity;

import javax.persistence.Table;

@Table(name = "t_sys_user_role")
public class SysUserRole {
	
	private String userId;
	
	private Integer roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	
}
