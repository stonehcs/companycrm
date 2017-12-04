package com.lichi.increaselimit.user.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统用户
 * 
 * @author majie
 *
 */
@Data
@ApiModel("系统用户信息")
public class SysUserDto implements Serializable {

	private static final long serialVersionUID = 720994283261142521L;

	@ApiModelProperty("手机号码")
	@NotNull(message = "手机号不能为空")
	private String mobile;

	@ApiModelProperty("验证码")
	@NotNull(message = "验证码不能为空")
	private String code;
	
	@ApiModelProperty("密码")
	@NotNull(message = "密码不能为空")
	private String password;
	
	

}
