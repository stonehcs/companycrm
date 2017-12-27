package com.lichi.increaselimit.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.vo.ResultVo;
import com.lichi.increaselimit.user.entity.UserVo;
import com.lichi.increaselimit.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Api(description = "客户信息管理")
@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {
	
	@Autowired
	private UserService userService;

	@GetMapping
	@ApiOperation("分页查询所有客户")
	public ResultVo<PageInfo<UserVo>> getAll(
			@ApiParam(value = "页码", required = false) @RequestParam(defaultValue = "1", required = false) Integer page,
			@ApiParam(value = "条数", required = false) @RequestParam(defaultValue = "20", required = false) Integer size) {
		log.info("分页查询所有客户");
		PageInfo<UserVo> list = userService.selectAll(page, size);
		return ResultVoUtil.success(list);
	}
	
	@GetMapping("/up/{pid}")
	@ApiOperation("查询选中客户的上层关系")
	public ResultVo<UserVo> getUp(@PathVariable String pid) {
		log.info("分页查询所有客户");
		UserVo list = userService.selectByPid(pid);
		return ResultVoUtil.success(list);
	}
}
