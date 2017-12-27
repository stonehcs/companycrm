package com.lichi.increaselimit.user.entity;

import io.swagger.annotations.ApiModelProperty;

public class UserVo extends User{

	private static final long serialVersionUID = 7544781771954429469L;

	@ApiModelProperty("邀请人")
	private String invitationtor;

	@ApiModelProperty("qq")
	private String qq;
	
	@ApiModelProperty("微信")
	private String weixin;
	
	@ApiModelProperty("微博")
	private String weibo;
	
	@ApiModelProperty("等级对应的金额")
	private Double levelMoney;
	
	@ApiModelProperty("客服等级")
	private String levelName;

	public String getInvitationtor() {
		return invitationtor;
	}

	public void setInvitationtor(String invitationtor) {
		this.invitationtor = invitationtor;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public Double getLevelMoney() {
		return levelMoney;
	}

	public void setLevelMoney(Double levelMoney) {
		this.levelMoney = levelMoney;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	

	
}
