package com.lichi.increaselimit.sys.controller.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * 授权接口
 * @author majie
 *
 */
public class GrantDto {
	
	@ApiModelProperty("0 锁   1解除锁定")
	@NotNull(message = "状态不能为空")
	private Integer locked;
	
	private List<String> ids;

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	
	
}
