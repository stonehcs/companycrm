package com.lichi.increaselimit.sys.controller.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysMenuUpdateDto {
	
	@ApiModelProperty("菜单id")
	@NotNull(message = "菜单id不能为空")
	private Integer id;
	
	@ApiModelProperty("菜单名称")
	private String menuName;
	
	@ApiModelProperty("父级菜单,如果是顶级菜单传-1")
	private Integer pid;
	
	@ApiModelProperty("备注")
	private String remark;
}
