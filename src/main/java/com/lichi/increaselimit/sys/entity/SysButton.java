package com.lichi.increaselimit.sys.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "t_sys_button")
@Data
public class SysButton {
	
	@Id
	private Integer id;
	
	private String buttonName;
	
	private Date createTime;
	
	private Date updateTime;
	
	private String url;
	
	private String method;
	
	private Integer menuId;
	
	private String remark;
}
