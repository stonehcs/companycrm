package com.lichi.increaselimit.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.sys.controller.dto.SysRoleResourceVo;
import com.lichi.increaselimit.sys.entity.SysRole;
import com.lichi.increaselimit.sys.entity.SysRoleResource;

public interface SysRoleService {

	PageInfo<SysRole> selectAll(Integer page, Integer size, String name);

	List<SysRole> selectList(String name);

	void insertOne(SysRole Role);

	SysRole selectOne(Integer id);

	void delete(List<Integer> ids);

	void addOrUpdate(SysRoleResourceVo vo);

	List<SysRoleResource> selectResource(Integer id, Integer menuId);
	
	
	/**
	 * 通过id获取用户角色信息
	 */
	List<SysRole> getUserRole(String id);

	List<SysRoleResource> selectUserResource(String userId);


}
