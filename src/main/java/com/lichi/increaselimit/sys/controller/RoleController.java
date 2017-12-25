package com.lichi.increaselimit.sys.controller;

import java.util.ArrayList;
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
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.vo.ResultVo;
import com.lichi.increaselimit.sys.controller.dto.SysRoleDto;
import com.lichi.increaselimit.sys.controller.dto.SysRoleUpdateDto;
import com.lichi.increaselimit.sys.controller.dto.SysRoleResourceDto;
import com.lichi.increaselimit.sys.entity.SysRole;
import com.lichi.increaselimit.sys.entity.SysRoleResource;
import com.lichi.increaselimit.sys.service.SysRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Api(description = "角色信息")
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

	@Autowired
	private SysRoleService roleService;

	@GetMapping
	@ApiOperation("分页查询所有角色")
	public ResultVo<PageInfo<SysRole>> getAll(
			@ApiParam(value = "页码", required = false) @RequestParam(defaultValue = "1", required = false) Integer page,
			@ApiParam(value = "条数", required = false) @RequestParam(defaultValue = "20", required = false) Integer size) {
		log.info("分页查询所有角色");
		PageInfo<SysRole> list = roleService.selectAll(page, size);
		return ResultVoUtil.success(list);
	}
	@GetMapping("/list")
	@ApiOperation("查询所有角色")
	public ResultVo<List<SysRole>> getAll() {
		log.info("查询所有角色");
		List<SysRole> list = roleService.selectList();
		return ResultVoUtil.success(list);
	}

	@PostMapping
	@ApiOperation("添加角色信息")
	public ResultVo<SysRole> add(@Valid @RequestBody SysRoleDto roleDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			log.error("添加角色信息参数错误:{}",errors);
			return ResultVoUtil.error(1, errors);
		}
		log.info("添加角色,角色名称:{}",roleDto.getRoleName());
		SysRole role = new SysRole();
		BeanUtils.copyProperties(roleDto, role);
		roleService.insertOne(role);
		return ResultVoUtil.success();
	}

	@PutMapping
	@ApiOperation("修改角色信息")
	public ResultVo<SysRole> update(@Valid @RequestBody SysRoleUpdateDto roleDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			log.error("修改角色信息参数错误:{}",errors);
			return ResultVoUtil.error(1, errors);
		}
		log.info("修改角色信息,角色名称:{}",roleDto.getRoleName());
		SysRole role = new SysRole();
		BeanUtils.copyProperties(roleDto, role);
		roleService.update(role);
		return ResultVoUtil.success();
	}

	@DeleteMapping("/{id}")
	@ApiOperation("根据id删除角色信息")
	public ResultVo<SysRole> delete(@PathVariable Integer id) {
		log.info("删除角色信息,角色id:{}",id);
		roleService.deleteOne(id);
		return ResultVoUtil.success();
	}

	@GetMapping("/{id}")
	@ApiOperation("根据id查询角色信息")
	public ResultVo<SysRole> get(@PathVariable Integer id) {
		log.info("查询角色信息,角色id:{}",id);
		SysRole list = roleService.selectOne(id);
		return ResultVoUtil.success(list);
	}
	
	@GetMapping("/like/{name}")
	@ApiOperation("根据角色名称模糊查询角色信息")
	public ResultVo<PageInfo<SysRole>> getLike(@PathVariable String name,
			@ApiParam(value = "页码", required = false) @RequestParam(defaultValue = "1", required = false) Integer page,
			@ApiParam(value = "条数", required = false) @RequestParam(defaultValue = "20", required = false) Integer size) {
		log.info("模糊查询角色信息,关键字:{}",name);
		PageInfo<SysRole> list = roleService.selectLike(name,page,size);
		return ResultVoUtil.success(list);
	}
	
	@PostMapping("/resource")
	@ApiOperation("添加角色资源")
	public ResultVo<SysRole> addResource(@Valid @RequestBody List<SysRoleResourceDto> list, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			log.error("添加角色资源参数错误:{}",errors);
			return ResultVoUtil.error(1, errors);
		}
		List<SysRoleResource> resultlist = new ArrayList<>();
		list.stream().forEach(e ->{
			if(-1 == e.getButtonId() && -1 == e.getMenuId()) {
				throw new BusinessException(ResultEnum.MUNE_BOTTUN_BOTH_NULL);
			}
			SysRoleResource sysRoleResource = new SysRoleResource();
			BeanUtils.copyProperties(e, sysRoleResource);
			resultlist.add(sysRoleResource);
		});
		roleService.addResource(resultlist);
		return ResultVoUtil.success();
	}
	
	@GetMapping("/resource/{id}")
	@ApiOperation("查询角色资源")
	public ResultVo<List<SysRoleResource>> selectResource(@PathVariable Integer id) {
		log.info("查询角色资源,角色id:{}",id);
		List<SysRoleResource> list = roleService.selectResource(id);
		return ResultVoUtil.success(list);
	}

}
