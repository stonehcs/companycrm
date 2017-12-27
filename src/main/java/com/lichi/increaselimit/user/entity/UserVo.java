package com.lichi.increaselimit.user.entity;

public class UserVo extends User{

	private static final long serialVersionUID = 7544781771954429469L;

	private String invitationtor;

	private String qq;
	
	private String weixin;
	
	private String weibo;
	
	private Double levelMoney;

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
	

	
}
