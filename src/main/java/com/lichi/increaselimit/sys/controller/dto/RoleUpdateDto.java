package com.lichi.increaselimit.sys.controller.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleUpdateDto {
	
	@ApiModelProperty("角色id")
	@NotNull(message = "角色id不能为空")
	private Integer id;
	
	@ApiModelProperty("角色名称")
	@NotBlank(message = "角色名称不能为空")
	private String roleName;
	
}