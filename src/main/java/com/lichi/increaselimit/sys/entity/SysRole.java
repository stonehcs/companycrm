package com.lichi.increaselimit.sys.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "t_sys_role")
@Data
public class SysRole {
	
	@Id
	private Integer id;
	
	private String roleName;
	
	private Date createTime;
	
	private Date updateTime;
	
}