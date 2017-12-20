package com.lichi.increaselimit.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.sys.entity.SysDept;

public interface SysDeptService {

	/**
	 * 分页查询所有部门
	 * @param page
	 * @param size
	 * @return
	 */
	PageInfo<SysDept> selectAll(Integer page, Integer size);

	/**
	 * 根据id查询部门
	 * @param id
	 * @return
	 */
	SysDept selectOne(Integer id);

	/**
	 * 新增一个部门
	 * @param dept
	 */
	void insertOne(SysDept dept);

	/**
	 * 删除部门
	 * @param id
	 */
	void deleteOne(Integer id);

	/**
	 * 更新部门信息
	 * @param dept
	 */
	void update(SysDept dept);

	/**
	 * 查询所有
	 * @return
	 */
	List<SysDept> selectList();

}
