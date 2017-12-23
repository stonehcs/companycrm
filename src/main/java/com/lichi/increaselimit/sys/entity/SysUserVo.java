package com.lichi.increaselimit.sys.entity;

import java.util.ArrayList;
import java.util.List;

public class SysUserVo extends SysUser{
	
	private static final long serialVersionUID = 5029223075005564544L;
	private String deptName;

	private List<SysRole> roles = new ArrayList<>();
	
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}


	
	
}
