package com.lichi.increaselimit.sys.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "t_sys_dept")
@Data
public class SysDept {
	
	@Id
	private Integer id;
	
	private String deptName;
	
	private Date createTime;
	
	private Date updateTime;
}