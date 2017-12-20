package com.lichi.increaselimit.sys.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.vo.ResultVo;
import com.lichi.increaselimit.sys.controller.dto.SysButtonDto;
import com.lichi.increaselimit.sys.controller.dto.SysButtonUpdateDto;
import com.lichi.increaselimit.sys.entity.SysButton;
import com.lichi.increaselimit.sys.service.SysButtonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "按钮信息")
@RestController
@RequestMapping("/button")
public class ButtonController {

	@Autowired
	private SysButtonService buttonService;
	
	@GetMapping("/list/{menuId}")
	@ApiOperation("查询所有按钮")
	public ResultVo<List<SysButton>> getAll(@PathVariable Integer menuId) {
		List<SysButton> list = buttonService.selectByMenuId(menuId);
		return ResultVoUtil.success(list);
	}

	@PostMapping
	@ApiOperation("添加按钮信息")
	public ResultVo<SysButton> add(@Valid @RequestBody SysButtonDto buttonDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			return ResultVoUtil.error(1, errors);
		}
		SysButton button = new SysButton();
		BeanUtils.copyProperties(buttonDto, button);
		buttonService.insertOne(button);
		return ResultVoUtil.success();
	}

	@PutMapping
	@ApiOperation("修改按钮信息")
	public ResultVo<SysButton> update(@Valid @RequestBody SysButtonUpdateDto buttonDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			return ResultVoUtil.error(1, errors);
		}
		SysButton button = new SysButton();
		BeanUtils.copyProperties(buttonDto, button);
		buttonService.update(button);
		return ResultVoUtil.success();
	}

	@DeleteMapping("/{id}")
	@ApiOperation("根据id删除按钮信息")
	public ResultVo<SysButton> delete(@PathVariable Integer id) {
		buttonService.deleteOne(id);
		return ResultVoUtil.success();
	}

	@GetMapping("/{id}")
	@ApiOperation("根据id查询按钮信息")
	public ResultVo<SysButton> get(@PathVariable Integer id) {
		SysButton list = buttonService.selectOne(id);
		return ResultVoUtil.success(list);
	}
}
