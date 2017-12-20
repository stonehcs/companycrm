package com.lichi.increaselimit.sys.controller.dto;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleDto {
	
	@ApiModelProperty("角色名称")
	@NotBlank(message = "角色名称不能为空")
	private String roleName;
	
}