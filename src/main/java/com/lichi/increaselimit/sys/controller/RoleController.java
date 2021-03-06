package com.lichi.increaselimit.sys.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.vo.ResultVo;
import com.lichi.increaselimit.sys.controller.dto.SysRoleResourceVo;
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

//	@GetMapping
//	@ApiOperation("分页查询所有角色")
//	public ResultVo<PageInfo<SysRole>> getAll(
//			@ApiParam(value = "页码", required = false) @RequestParam(defaultValue = "1", required = false) Integer page,
//			@ApiParam(value = "条数", required = false) @RequestParam(defaultValue = "20", required = false) Integer size,
//			@ApiParam(value = "关键字", required = false) @RequestParam(required = false) String name) {
//		log.info("分页查询所有角色");
//		PageInfo<SysRole> list = roleService.selectAll(page, size ,name);
//		return ResultVoUtil.success(list);
//	}
	
	@GetMapping("/list")
	@ApiOperation("查询所有角色")
	public ResultVo<List<SysRole>> getAll(@ApiParam(value = "关键字", required = false) @RequestParam(required = false) String name) {
		log.info("查询所有角色");
		List<SysRole> list = roleService.selectList(name);
		return ResultVoUtil.success(list);
	}

	@DeleteMapping
	@ApiOperation("根据id删除角色信息")
	public ResultVo<SysRole> delete(@RequestBody List<Integer> ids) {
		log.info("删除角色信息,角色id:{}",ids);
		if(null == ids || ids.size() <=0) {
			throw new BusinessException(ResultEnum.NO_ROLE_ID);
		}
		roleService.delete(ids);
		return ResultVoUtil.success();
	}
	

	@GetMapping("/{id}")
	@ApiOperation("根据id查询角色信息")
	public ResultVo<SysRole> get(@PathVariable Integer id) {
		log.info("查询角色信息,角色id:{}",id);
		SysRole list = roleService.selectOne(id);
		return ResultVoUtil.success(list);
	}
	
	@PostMapping("/resource")
	@ApiOperation("添加或者修改角色资源")
	public ResultVo<SysRole> addResource(@Valid @RequestBody SysRoleResourceVo vo, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			log.warn("添加角色资源参数错误:{}",errors);
			return ResultVoUtil.error(1, errors);
		}
		roleService.addOrUpdate(vo);
		return ResultVoUtil.success();
	}
	
	@GetMapping("/resource/{roleId}/{menuId}")
	@ApiOperation("查询角色资源")
	public ResultVo<List<SysRoleResource>> selectResource(@PathVariable Integer roleId,@PathVariable Integer menuId) {
		log.info("查询角色资源,角色id:{},菜单id:{}",roleId,menuId);
		List<SysRoleResource> list = roleService.selectResource(roleId,menuId);
		return ResultVoUtil.success(list);
	}

}
