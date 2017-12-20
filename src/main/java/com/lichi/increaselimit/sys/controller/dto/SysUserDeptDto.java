package com.lichi.increaselimit.sys.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统用户
 * 
 * @author majie
 *
 */
@Data
public class SysUserDeptDto implements Serializable {


	private static final long serialVersionUID = -5563804047432812715L;

	@ApiModelProperty("id")
	@NotBlank(message = "请选择要修改的用户")
	private String id;
	
	@ApiModelProperty("部门id")
	@NotNull(message = "请选择要修改的部门")
	private Integer deptId;

}
