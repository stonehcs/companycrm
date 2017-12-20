package com.lichi.increaselimit.sys.controller.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysButtonUpdateDto {
	
	@ApiModelProperty("id")
	@NotNull(message = "id不能为空")
	private Integer id;
	
	@ApiModelProperty("按钮名称")
	private String buttonName;
	
	@ApiModelProperty("请求路径")
	private String url;
	
	@ApiModelProperty("请求方法")
	private String method;
	
//	@ApiModelProperty("菜单id")
//	private Integer muneId;
	
	@ApiModelProperty("备注")
	private String remark;
}
