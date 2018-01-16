package com.lichi.increaselimit.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
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
	
	@ApiModelProperty("头像")
	private String headImg;
	
	@JsonIgnore
	private String password;
	
	@ApiModelProperty("手机号")
	private String mobile;
	
	@ApiModelProperty("等级")
	private Integer vipLevel;
	
	/**
	 * 佣金
	 */
	@ApiModelProperty("佣金")
	private Double money;
	
	@ApiModelProperty("用户名")
	private String nickname;

	/**
	 * 邀请人数
	 */
	@ApiModelProperty("邀请人数")
	private Integer rank;
	
	/**
	 * 积分
	 */
	@ApiModelProperty("积分")
	private Integer points;
	
	private Date updateTime;
	private Date createTime;
	
	@ApiModelProperty("pid")
	private String pid;
	
	@ApiModelProperty("真实人数")
	private Integer invitation;
	
}
