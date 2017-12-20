package com.lichi.increaselimit.sys.controller.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeptUpdateDto {
	
	@ApiModelProperty("部门id")
	@NotNull(message = "部门id不能为空")
	private Integer id;
	
	@ApiModelProperty("部门名称")
	@NotBlank(message = "部门名称不能为空")
	private String deptName;
	
}