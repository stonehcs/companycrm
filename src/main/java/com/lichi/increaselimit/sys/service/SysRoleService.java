package com.lichi.increaselimit.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.sys.entity.SysRole;
import com.lichi.increaselimit.sys.entity.SysRoleResource;

public interface SysRoleService {

	PageInfo<SysRole> selectAll(Integer page, Integer size);

	List<SysRole> selectList();

	void insertOne(SysRole Role);

	void update(SysRole Role);

	SysRole selectOne(Integer id);

	void deleteOne(Integer id);

	void addResource(List<SysRoleResource> resultlist);

	List<SysRoleResource> selectResource(Integer id);
	
	
	/**
	 * 通过id获取用户角色信息
	 */
	List<SysRole> getUserRole(String id);

	/**
	 * 模糊查询
	 * @param name
	 * @param size 
	 * @param page 
	 * @return
	 */
	PageInfo<SysRole> selectLike(String name, Integer page, Integer size);


}
