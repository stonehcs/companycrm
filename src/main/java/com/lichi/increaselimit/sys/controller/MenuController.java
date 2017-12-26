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

import com.alibaba.fastjson.JSONArray;
import com.lichi.increaselimit.common.utils.MenuTreeUtils;
import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.vo.ResultVo;
import com.lichi.increaselimit.sys.controller.dto.SysMenuDto;
import com.lichi.increaselimit.sys.controller.dto.SysMenuUpdateDto;
import com.lichi.increaselimit.sys.entity.SysMenu;
import com.lichi.increaselimit.sys.service.SysMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(description = "菜单信息")
@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController {

	@Autowired
	private SysMenuService menuService;

	@GetMapping("/tree")
	@ApiOperation("查询菜单树")
	public Object getAll() {
		
		log.info("查询菜单树");
		List<SysMenu> list = menuService.selectList();

		JSONArray jsonArray = MenuTreeUtils.treeMenuList(list, -1);

		return ResultVoUtil.success(jsonArray);
	}

	@PostMapping
	@ApiOperation("添加菜单信息")
	public ResultVo<SysMenu> add(@Valid @RequestBody SysMenuDto menuDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			log.error("添加菜单信息参数错误:{}",errors);
			return ResultVoUtil.error(1, errors);
		}
		log.info("添加菜单信息,菜单名称:{}",menuDto.getMenuName());
		SysMenu menu = new SysMenu();
		BeanUtils.copyProperties(menuDto, menu);
		menuService.insertOne(menu);
		return ResultVoUtil.success();
	}

	@PutMapping
	@ApiOperation("修改菜单信息")
	public ResultVo<SysMenu> update(@Valid @RequestBody SysMenuUpdateDto menuDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			log.error("修改菜单信息参数错误:{}",errors);
			return ResultVoUtil.error(1, errors);
		}
		log.info("修改菜单信息,菜单名称:{}",menuDto.getMenuName());
		SysMenu menu = new SysMenu();
		BeanUtils.copyProperties(menuDto, menu);
		menuService.update(menu);
		return ResultVoUtil.success();
	}

	@DeleteMapping("/{id}")
	@ApiOperation("根据id删除菜单信息")
	public ResultVo<SysMenu> delete(@PathVariable Integer id) {
		log.info("删除菜单信息,菜单id:{}",id);
		menuService.deleteOne(id);
		return ResultVoUtil.success();
	}

	@GetMapping("/{id}")
	@ApiOperation("根据id查询菜单信息")
	public ResultVo<SysMenu> get(@PathVariable Integer id) {
		log.info("查询菜单信息,菜单id:{}",id);
		SysMenu list = menuService.selectOne(id);
		return ResultVoUtil.success(list);
	}
}
