package com.lichi.increaselimit.sys.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 版本信息
 * @author majie
 *
 */
@Data
@Table(name = "t_version")
public class Version {

	@Id
	private Integer id;
	
	private String version;
	
	private Integer type;
	
	private String information;
	
	private String url;
	
	private Integer status;
	
	private Date createTime;
	
	private Date updateTime;
	
	
}
