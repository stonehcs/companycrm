package com.lichi.increaselimit.sys.service;

import java.util.List;

import com.lichi.increaselimit.sys.entity.SysButton;

public interface SysButtonService {

	/**
	 * 根据id查询按钮
	 * @param id
	 * @return
	 */
	SysButton selectOne(Integer id);

	/**
	 * 新增一个按钮
	 * @param button
	 */
	void insertOne(SysButton button);

	/**
	 * 删除按钮
	 * @param id
	 */
	void deleteOne(Integer id);

	/**
	 * 更新按钮信息
	 * @param dept
	 */
	void update(SysButton dept);

	/**
	 * 查询所有
	 * @return
	 */
	List<SysButton> selectByMenuId(Integer menuId);

}
