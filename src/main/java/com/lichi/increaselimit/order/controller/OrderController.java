package com.lichi.increaselimit.order.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.vo.ResultVo;
import com.lichi.increaselimit.order.controller.dto.OrderUpdateDto;
import com.lichi.increaselimit.order.entity.Order;
import com.lichi.increaselimit.order.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Api(description = "订单信息")
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	@ApiOperation("分页查询所有订单")
	public ResultVo<PageInfo<Order>> getAll(
			@ApiParam(value = "页码", required = false) @RequestParam(defaultValue = "1", required = false) Integer page,
			@ApiParam(value = "条数", required = false) @RequestParam(defaultValue = "20", required = false) Integer size) {
		log.info("分页查询所有订单");
		PageInfo<Order> list = orderService.selectAll(page, size);
		return ResultVoUtil.success(list);
	}

	@PutMapping
	@ApiOperation("修改订单信息")
	public ResultVo<Order> update(@Valid @RequestBody OrderUpdateDto orderDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			log.warn("修改订单信息参数错误:{}" + errors);
			return ResultVoUtil.error(1, errors);
		}
		log.info("修改订单,订单id:{}",orderDto.getId());
		Order order = new Order();
		BeanUtils.copyProperties(orderDto, order);
		orderService.update(order);
		return ResultVoUtil.success();
	}

	@DeleteMapping("/{id}")
	@ApiOperation("根据id删除订单信息")
	public ResultVo<Order> delete(@PathVariable String id) {
		log.info("删除订单信息,订单id:{}",id);
		orderService.deleteOne(id);
		return ResultVoUtil.success();
	}

	@GetMapping("/{id}")
	@ApiOperation("根据id查询订单信息")
	public ResultVo<Order> get(@PathVariable String id) {
		log.info("查询订单信息,订单id:{}",id);
		Order list = orderService.selectOne(id);
		return ResultVoUtil.success(list);
	}
}
