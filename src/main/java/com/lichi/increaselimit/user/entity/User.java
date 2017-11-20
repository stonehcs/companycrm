package com.lichi.increaselimit.user.entity;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 用户信息
 * @author majie
 *
 */
@Data
@Table(name = "t_user")
public class User {
	
	@Id
	private Integer id;
	
	private String headImg;
	
	private String password;
	
	private String mobile;
	
	private String qq;
	
	private String weixin;
	
	private Integer vipLevel;
	
	private BigDecimal money;
	
	private String username;
	
	private String sss;
}
