package com.lichi.increaselimit.sys.service;

import java.util.List;

import com.lichi.increaselimit.sys.entity.SysMenu;

public interface SysMenuService {

	/**
	 * 根据id查询菜单
	 * @param id
	 * @return
	 */
	SysMenu selectOne(Integer id);

	/**
	 * 新增一个菜单
	 * @param Mune
	 */
	void insertOne(SysMenu mune);

	/**
	 * 删除菜单
	 * @param id
	 */
	void deleteOne(Integer id);

	/**
	 * 更新菜单信息
	 * @param Mune
	 */
	void update(SysMenu mune);

	/**
	 * 查询所有
	 * @return
	 */
	List<SysMenu> selectList();

	/**
	 * 通过用户id查询该用户拥有的菜单
	 */
	List<SysMenu> selectByUserId(String userId);
}
