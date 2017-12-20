package com.lichi.increaselimit.sys.controller.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysMenuDto {
	
	@ApiModelProperty("菜单名称")
	@NotBlank(message = "菜单名称不能为空")
	private String menuName;
	
	@ApiModelProperty("父级菜单,如果是顶级菜单传-1")
	@NotNull(message = "父级菜单不能为空")
	private Integer parent;
	
	@ApiModelProperty("备注")
	private String remark;
}
