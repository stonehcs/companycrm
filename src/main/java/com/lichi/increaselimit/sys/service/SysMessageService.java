package com.lichi.increaselimit.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.sys.entity.SysMessage;

public interface SysMessageService {

	/**
	 * 分页查询所有系统消息
	 * @param page
	 * @param size
	 * @return
	 */
	PageInfo<SysMessage> selectAll(Integer page, Integer size);

	/**
	 * 根据id查询系统消息
	 * @param id
	 * @return
	 */
	SysMessage selectOne(Integer id);

	/**
	 * 新增一个系统消息
	 * @param Message
	 */
	void insertOne(SysMessage message);

	/**
	 * 删除系统消息
	 * @param id
	 */
	void deleteOne(Integer id);

	/**
	 * 更新系统消息信息
	 * @param Message
	 */
	void update(SysMessage message);

	/**
	 * 查询所有
	 * @return
	 */
	List<SysMessage> selectList();

}
