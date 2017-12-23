package com.lichi.increaselimit.sys.controller.dto;

import java.io.Serializable;
import java.util.List;

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
public class SysUpdateDto implements Serializable {


	private static final long serialVersionUID = -5563804047432812715L;

	@ApiModelProperty("id")
	@NotBlank(message = "请选择要修改的用户")
	private String id;
	
	@ApiModelProperty("用户昵称")
	private String nickname;
	
	@ApiModelProperty("部门id")
	private Integer deptId;
	
	@ApiModelProperty("角色ids")
	private List<Integer> roleIds;
	

}
