package com.lichi.increaselimit.sys.controller.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysRoleResourceDto {
	
	@ApiModelProperty("角色id")
	@NotNull(message = "角色id不能为空")
	private Integer roleId;
	
	@ApiModelProperty("菜单id")
	private Integer menuId = -1;
	
	@ApiModelProperty("按钮id")
	private Integer buttonId = -1;
	
	@ApiModelProperty("类型0是菜单   1是按钮")
	@NotNull(message = "类型不能为空")
	private Integer type;
	
	
}