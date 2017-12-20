package com.lichi.increaselimit.sys.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "t_sys_menu")
@Data
public class SysMenu {
	
	@Id
	private Integer id;
	
	private String menuName;
	
	private Integer pid;
	
	private Date createTime;
	
	private Date updateTime;
	
	private String remark;
	
}
