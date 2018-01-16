package com.lichi.increaselimit.sys.controller.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

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
//	@NotBlank(message = "请选择要修改的用户")
	private String id;
	
	@ApiModelProperty("手机号码")
	@NotBlank(message = "手机号不能为空")
	private String mobile;

	@ApiModelProperty("密码")
	@NotBlank(message = "密码不能为空")
	private String password;
	
	
	@ApiModelProperty("验证码")
	@NotBlank(message = "验证码不能为空")
	private String code;

}
