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
import com.lichi.increaselimit.sys.controller.dto.RoleDto;
import com.lichi.increaselimit.sys.controller.dto.RoleUpdateDto;
import com.lichi.increaselimit.sys.entity.Role;
import com.lichi.increaselimit.sys.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description = "角色信息")
@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping
	@ApiOperation("分页查询所有角色")
	public ResultVo<PageInfo<Role>> getAll(
			@ApiParam(value = "页码", required = false) @RequestParam(defaultValue = "1", required = false) Integer page,
			@ApiParam(value = "条数", required = false) @RequestParam(defaultValue = "20", required = false) Integer size) {
		PageInfo<Role> list = roleService.selectAll(page, size);
		return ResultVoUtil.success(list);
	}
	@GetMapping("/list")
	@ApiOperation("查询所有角色")
	public ResultVo<List<Role>> getAll() {
		List<Role> list = roleService.selectList();
		return ResultVoUtil.success(list);
	}

	@PostMapping
	@ApiOperation("添加角色信息")
	public ResultVo<Role> add(@Valid @RequestBody RoleDto RoleDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			return ResultVoUtil.error(1, errors);
		}
		Role Role = new Role();
		BeanUtils.copyProperties(RoleDto, Role);
		roleService.insertOne(Role);
		return ResultVoUtil.success();
	}

	@PutMapping
	@ApiOperation("修改角色信息")
	public ResultVo<Role> update(@Valid @RequestBody RoleUpdateDto RoleDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			return ResultVoUtil.error(1, errors);
		}
		Role Role = new Role();
		BeanUtils.copyProperties(RoleDto, Role);
		roleService.update(Role);
		return ResultVoUtil.success();
	}

	@DeleteMapping("/{id}")
	@ApiOperation("根据id删除角色信息")
	public ResultVo<Role> delete(@PathVariable Integer id) {
		roleService.deleteOne(id);
		return ResultVoUtil.success();
	}

	@GetMapping("/{id}")
	@ApiOperation("根据id查询角色信息")
	public ResultVo<Role> get(@PathVariable Integer id) {
		Role list = roleService.selectOne(id);
		return ResultVoUtil.success(list);
	}
}
