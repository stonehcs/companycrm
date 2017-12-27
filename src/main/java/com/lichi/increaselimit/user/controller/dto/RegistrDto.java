package com.lichi.increaselimit.user.controller.dto;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrDto {
	
	
	@ApiModelProperty("pid")
	@NotBlank(message = "pid不能为空")
	private String pid;
	
	@ApiModelProperty("手机号")
	@NotBlank(message = "手机号不能为空")
	private String mobile;
	
	@ApiModelProperty("验证码")
	@NotBlank(message = "验证码不能为空")
	private String code;
	
	
}
