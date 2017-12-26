package com.lichi.increaselimit.sys.controller.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysRoleResourceDto {
	
	@ApiModelProperty("菜单id")
	private Integer menuId;
	
	@ApiModelProperty("按钮id")
	private Integer buttonId;
	
	@ApiModelProperty("类型0是菜单   1是按钮")
	@NotNull(message = "类型不能为空")
	private Integer type;
	
	
}