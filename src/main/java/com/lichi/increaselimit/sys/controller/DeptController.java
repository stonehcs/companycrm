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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.vo.ResultVo;
import com.lichi.increaselimit.sys.controller.dto.DeptDto;
import com.lichi.increaselimit.sys.controller.dto.DeptUpdateDto;
import com.lichi.increaselimit.sys.entity.Dept;
import com.lichi.increaselimit.sys.service.DeptService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description = "部门信息")
@RestController
@RequestMapping("/dept")
public class DeptController {

	@Autowired
	private DeptService deptService;

	@GetMapping
	@ApiOperation("分页查询所有部门")
	public ResultVo<PageInfo<Dept>> getAll(
			@ApiParam(value = "页码", required = false) @RequestParam(defaultValue = "1", required = false) Integer page,
			@ApiParam(value = "条数", required = false) @RequestParam(defaultValue = "20", required = false) Integer size) {
		PageInfo<Dept> list = deptService.selectAll(page, size);
		return ResultVoUtil.success(list);
	}
	@GetMapping("/list")
	@ApiOperation("查询所有部门")
	public ResultVo<List<Dept>> getAll() {
		List<Dept> list = deptService.selectList();
		return ResultVoUtil.success(list);
	}

	@PostMapping
	@ApiOperation("添加部门信息")
	public ResultVo<Dept> add(@Valid @RequestBody DeptDto deptDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			return ResultVoUtil.error(1, errors);
		}
		Dept dept = new Dept();
		BeanUtils.copyProperties(deptDto, dept);
		deptService.insertOne(dept);
		return ResultVoUtil.success();
	}

	@PutMapping
	@ApiOperation("修改部门信息")
	public ResultVo<Dept> update(@Valid @RequestBody DeptUpdateDto deptDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			return ResultVoUtil.error(1, errors);
		}
		Dept dept = new Dept();
		BeanUtils.copyProperties(deptDto, dept);
		deptService.update(dept);
		return ResultVoUtil.success();
	}

	@DeleteMapping("/{id}")
	@ApiOperation("根据id删除部门信息")
	public ResultVo<Dept> delete(@PathVariable Integer id) {
		deptService.deleteOne(id);
		return ResultVoUtil.success();
	}

	@GetMapping("/{id}")
	@ApiOperation("根据id查询部门信息")
	public ResultVo<Dept> get(@PathVariable Integer id) {
		Dept list = deptService.selectOne(id);
		return ResultVoUtil.success(list);
	}
}
