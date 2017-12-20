package com.lichi.increaselimit.sys.entity;

import javax.persistence.Table;

import lombok.Data;

@Table(name = "t_sys_role_resource")
@Data
public class SysRoleResource {
	
	private Integer roleId;
	
	private Integer menuId;
	
	private Integer buttonId;
	
	private Integer type;
	
}
