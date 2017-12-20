package com.lichi.increaselimit.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.sys.entity.Dept;

public interface DeptService {

	/**
	 * 分页查询所有部门
	 * @param page
	 * @param size
	 * @return
	 */
	PageInfo<Dept> selectAll(Integer page, Integer size);

	/**
	 * 根据id查询部门
	 * @param id
	 * @return
	 */
	Dept selectOne(Integer id);

	/**
	 * 新增一个部门
	 * @param dept
	 */
	void insertOne(Dept dept);

	/**
	 * 删除部门
	 * @param id
	 */
	void deleteOne(Integer id);

	/**
	 * 更新部门信息
	 * @param dept
	 */
	void update(Dept dept);

	/**
	 * 查询所有
	 * @return
	 */
	List<Dept> selectList();

}
