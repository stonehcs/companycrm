package com.lichi.increaselimit.sys.controller.dto;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysDeptDto {
	
	@ApiModelProperty("部门名称")
	@NotBlank(message = "部门名称不能为空")
	private String deptName;
	
}