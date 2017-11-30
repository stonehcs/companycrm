package com.lichi.increaselimit.user.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
	
	private BigDecimal money;
	
	private String username;
	
}
