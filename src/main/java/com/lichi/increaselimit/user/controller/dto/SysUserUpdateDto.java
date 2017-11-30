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
@ApiModel("更新系统用户信息")
public class SysUserUpdateDto implements Serializable {

	private static final long serialVersionUID = 720994283261142521L;
	

	@ApiModelProperty("id")
	@NotNull(message = "请选择要修改的用户")
	private Integer id;
	
	@ApiModelProperty("用户名")
	private String username;

	@ApiModelProperty("手机号码")
	@NotNull(message = "手机号不能为空")
	private String mobile;

	@ApiModelProperty("密码")
	@NotNull(message = "密码不能为空")
	private String password;
	
	
	@ApiModelProperty("验证码")
	@NotNull(message = "验证码不能为空")
	private String code;

}
