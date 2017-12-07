package com.lichi.increaselimit.community.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

/**
 * 圈子
 * 
 * @author by majie on 2017/11/16.
 */
@Data
@Table(name = "t_circle")
public class Circle implements Serializable {

	private static final long serialVersionUID = 8344002416206506566L;

	@Id
	private Integer id;

	private String name;

	@Column(updatable = false)
	private String createUserId;

	@Column(updatable = false)
	private Date createTime;

	private Date updateTime;

	private String url;
	
	private String introduce;

	@Transient
	private Integer count = 0;

	private Integer sort1;

	private Integer sort2;
}
