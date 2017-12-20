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
import com.lichi.increaselimit.sys.controller.dto.SysMenuDto;
import com.lichi.increaselimit.sys.controller.dto.SysMenuUpdateDto;
import com.lichi.increaselimit.sys.entity.SysMenu;
import com.lichi.increaselimit.sys.service.SysMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "菜单信息")
@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private SysMenuService menuService;

	@GetMapping("/tree")
	@ApiOperation("查询菜单树,暂时是列表  要修改")
	public ResultVo<List<SysMenu>> getAll() {
		List<SysMenu> list = menuService.selectList();
		return ResultVoUtil.success(list);
	}

	@PostMapping
	@ApiOperation("添加菜单信息")
	public ResultVo<SysMenu> add(@Valid @RequestBody SysMenuDto MenuDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			return ResultVoUtil.error(1, errors);
		}
		SysMenu Menu = new SysMenu();
		BeanUtils.copyProperties(MenuDto, Menu);
		menuService.insertOne(Menu);
		return ResultVoUtil.success();
	}

	@PutMapping
	@ApiOperation("修改菜单信息")
	public ResultVo<SysMenu> update(@Valid @RequestBody SysMenuUpdateDto MenuDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			return ResultVoUtil.error(1, errors);
		}
		SysMenu Menu = new SysMenu();
		BeanUtils.copyProperties(MenuDto, Menu);
		menuService.update(Menu);
		return ResultVoUtil.success();
	}

	@DeleteMapping("/{id}")
	@ApiOperation("根据id删除菜单信息")
	public ResultVo<SysMenu> delete(@PathVariable Integer id) {
		menuService.deleteOne(id);
		return ResultVoUtil.success();
	}

	@GetMapping("/{id}")
	@ApiOperation("根据id查询菜单信息")
	public ResultVo<SysMenu> get(@PathVariable Integer id) {
		SysMenu list = menuService.selectOne(id);
		return ResultVoUtil.success(list);
	}
}
