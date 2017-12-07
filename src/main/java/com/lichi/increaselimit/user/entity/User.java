package com.lichi.increaselimit.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * 用户信息
 * @author majie
 *
 */
@Data
@Table(name = "t_user")
public class User implements Serializable{
	
	private static final long serialVersionUID = -368895461220621034L;

	@Id
	private String id;
	
	private String headImg;
	
	@JsonIgnore
	private String password;
	
	private String mobile;
	
	private Integer vipLevel;
	
	/**
	 * 佣金
	 */
	private Double money;
	
	@JsonIgnore
	private String username;
	
	private String nickname;

	/**
	 * 邀请人数
	 */
	private Integer rank;
	
	/**
	 * 积分
	 */
	private Integer points;
	
	private Date updateTime;
	private Date createTime;
}
