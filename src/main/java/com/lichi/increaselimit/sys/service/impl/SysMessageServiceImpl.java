package com.lichi.increaselimit.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.utils.JpushClientUtil;
import com.lichi.increaselimit.sys.dao.SysMessageDao;
import com.lichi.increaselimit.sys.entity.SysMessage;
import com.lichi.increaselimit.sys.service.SysMessageService;

@Service
public class SysMessageServiceImpl implements SysMessageService{
	@Autowired
	private SysMessageDao messageDao;

	@Override
	public PageInfo<SysMessage> selectAll(Integer page, Integer size) {
		PageHelper.startPage(page, size);
		PageHelper.orderBy("create_time desc");
		List<SysMessage> list = messageDao.selectAll();
		PageInfo<SysMessage> info = new PageInfo<>(list);
		return info;
	}

	@Override
	public SysMessage selectOne(Integer id) {
		return messageDao.selectByPrimaryKey(id);
	}

	@Override
	@Async
	public void insertOne(SysMessage message) {
		message.setUpdateTime(new Date());
		message.setCreateTime(new Date());
		messageDao.insertSelective(message);
		
		JpushClientUtil.pushToAll(message.getTitle(), message.getContent());
	}

	@Override
	public void deleteOne(Integer id) {
		messageDao.deleteByPrimaryKey(id);
	}

	@Override
	public void update(SysMessage Message) {
		Message.setUpdateTime(new Date());
		messageDao.updateByPrimaryKeySelective(Message);
		
	}

	@Override
	public List<SysMessage> selectList() {
		PageHelper.orderBy("create_time desc");
		List<SysMessage> list = messageDao.selectAll();
		return list;
	}

}
