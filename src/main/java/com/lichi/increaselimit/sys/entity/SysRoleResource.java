package com.lichi.increaselimit.sys.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Table(name = "t_sys_role_resource")
@Data
@Entity
public class SysRoleResource {
	
	private Integer roleId;
	
	private Integer menuId;
	
	private Integer buttonId;
	
	private Integer type;

	@Transient
	private String buttonName;
	@Transient
	private String menuName;
	
}
