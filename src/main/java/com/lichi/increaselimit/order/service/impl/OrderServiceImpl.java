package com.lichi.increaselimit.order.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.order.dao.OrderDao;
import com.lichi.increaselimit.order.entity.Order;
import com.lichi.increaselimit.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public void insert(Order order) {
		orderDao.insertSelective(order);
	}

	@Override
	public PageInfo<Order> selectAll(Integer page, Integer size) {
		PageHelper.startPage(page, size);
		List<Order> list = orderDao.selectAll();
		PageInfo<Order> info = new PageInfo<>(list);
		return info;
	}

	@Override
	public void update(Order order) {
		order.setUpdateTime(new Date());
		orderDao.updateByPrimaryKeySelective(order);
	}

	@Override
	public void deleteOne(String id) {
		orderDao.deleteByPrimaryKey(id);
	}

	@Override
	public Order selectOne(String id) {
		return orderDao.selectByPrimaryKey(id);
	}

}
