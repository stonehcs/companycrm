package com.lichi.increaselimit.community.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 圈子
 * 
 * @author by majie on 2017/11/16.
 */
@Data
public class Circle implements Serializable {

	private static final long serialVersionUID = 8344002416206506566L;

	private Integer id;

	private String name;

	private String createUserId;

	private Date createTime;

	private Date updateTime;

	private String url;

	private Integer count;
}
