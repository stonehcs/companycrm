package com.lichi.increaselimit.user.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 下层顾客
 * @author majie
 *
 */
public class UserShare implements Serializable{

	private static final long serialVersionUID = -3349964632040810510L;

	private String id;
	
	@ApiModelProperty("头像")
	private String headImg;
	
	@ApiModelProperty("用户名")
	private String nickname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
