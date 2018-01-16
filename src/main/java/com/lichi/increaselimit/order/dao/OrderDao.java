package com.lichi.increaselimit.order.dao;

import org.apache.ibatis.annotations.Mapper;

import com.lichi.increaselimit.common.mapper.BaseMapper;
import com.lichi.increaselimit.order.entity.Order;

@Mapper
public interface OrderDao extends BaseMapper<Order>{

}
