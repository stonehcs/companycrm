package com.lichi.increaselimit.sys.controller.dto;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class SysRoleResourceVo {
	
	@ApiModelProperty("角色名称")
	@NotBlank(message = "角色名称不能为空")
	private String roleName;
	
	@ApiModelProperty("角色id")
	private Integer id;
	
	private List<SysRoleResourceDto> list ;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<SysRoleResourceDto> getList() {
		return list;
	}

	public void setList(List<SysRoleResourceDto> list) {
		this.list = list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
}