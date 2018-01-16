package com.lichi.increaselimit.order.service;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.order.entity.Order;

public interface OrderService {

	/**
	 * 添加订单
	 * @param order
	 */
	void insert(Order order);

	PageInfo<Order> selectAll(Integer page, Integer size);

	void update(Order order);

	void deleteOne(String id);

	Order selectOne(String id);

}
