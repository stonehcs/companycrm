package com.lichi.increaselimit.sys.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Table(name = "t_sys_role")
@Data
public class SysRole {
	
	@Id
	private Integer id;
	
	private String roleName;
	
	private Date createTime;
	
	private Date updateTime;
	
	@Transient
	List<SysRoleResource> resources = new ArrayList<>();
	
}