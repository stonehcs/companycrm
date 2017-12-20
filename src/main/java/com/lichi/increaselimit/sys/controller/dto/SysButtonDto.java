package com.lichi.increaselimit.sys.controller.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysButtonDto {
	
	@ApiModelProperty("按钮名称")
	@NotBlank(message = "按钮名称不能为空")
	private String buttonName;
	
	@ApiModelProperty("请求路径")
	@NotBlank(message = "路径名称不能为空")
	private String url;
	
	@ApiModelProperty("请求方法")
	@NotBlank(message = "请求方法不能为空")
	private String method;
	
	@ApiModelProperty("菜单id")
	@NotNull(message = "菜单id不能为空")
	private Integer menuId;
	
	@ApiModelProperty("备注")
	private String remark;
}
