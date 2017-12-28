package com.lichi.increaselimit.sys.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.Constants;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.common.utils.MenuTreeUtils;
import com.lichi.increaselimit.common.utils.RedisUtils;
import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.utils.StringUtil;
import com.lichi.increaselimit.common.vo.ResultVo;
import com.lichi.increaselimit.security.validate.code.ValidateCode;
import com.lichi.increaselimit.sys.controller.dto.SysUpdateDto;
import com.lichi.increaselimit.sys.controller.dto.SysUserDto;
import com.lichi.increaselimit.sys.controller.dto.SysUserUpdateDto;
import com.lichi.increaselimit.sys.entity.SysMenu;
import com.lichi.increaselimit.sys.entity.SysRole;
import com.lichi.increaselimit.sys.entity.SysUser;
import com.lichi.increaselimit.sys.entity.SysUserVo;
import com.lichi.increaselimit.sys.service.SysMenuService;
import com.lichi.increaselimit.sys.service.SysRoleService;
import com.lichi.increaselimit.sys.service.SysUserService;
import com.lichi.increaselimit.user.entity.User;
import com.lichi.increaselimit.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统用户信息controller
 * 
 * @author majie
 *
 */
@Api(description = "系统用户信息")
@RestController
@RequestMapping("/sysuser")
@Slf4j
public class SysUserController {

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private UserService userService;

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysMenuService sysMenuService;

	@PostMapping("/regiter")
	@ApiOperation("注册，调用此接口请先调用发送验证码的接口")
	public ResultVo<SysUser> register(@Valid @RequestBody SysUserDto sysUserDto, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			log.error("用户注册参数错误:{}", errors);
			return ResultVoUtil.error(1, errors);
		}
		if (!StringUtil.ValidateMobile(sysUserDto.getMobile())) {
			throw new BusinessException(ResultEnum.MOBILE_ERROR);
		}
		String json = redisUtils.get(Constants.MOBILE_REDIS_KEY + sysUserDto.getMobile());

		if (StringUtils.isBlank(json)) {
			throw new BusinessException(ResultEnum.VALIDATECODE_TIMEOUT);
		}

		ValidateCode code = JSONObject.parseObject(json, ValidateCode.class);
		if (!sysUserDto.getCode().equals(code.getCode())) {
			throw new BusinessException(ResultEnum.VALIDATECODE_ERROR);
		}

		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(sysUserDto, sysUser);

		log.info("用户注册,手机号码:{}", sysUser.getMobile());

		// 注册的时候用户名默认为手机号码
		sysUser.setUsername(sysUser.getMobile());
		sysUser.setNickname(sysUser.getMobile());
		sysUserService.insertUser(sysUser);

		return ResultVoUtil.success();
	}

	@GetMapping("/all")
	@ApiOperation("分页查询所有用户")
	public ResultVo<PageInfo<SysUserVo>> getAll(
			@ApiParam(value = "页码", required = false) @RequestParam(defaultValue = "1", required = false) Integer page,
			@ApiParam(value = "条数", required = false) @RequestParam(defaultValue = "20", required = false) Integer size,
			@ApiParam(value = "模糊查询条件", required = false) @RequestParam(required = false) String key) {
		log.info("分页查询所有用户");
		PageInfo<SysUserVo> list = sysUserService.selectAll(page, size, key);
		return ResultVoUtil.success(list);
	}

	@DeleteMapping
	@ApiOperation("删除系统用户")
	public ResultVo<SysUser> deleteSysUser(@RequestBody List<String> ids) {
		log.info("删除系统用户信息,ids:{}", ids);
		if (null == ids || ids.size() <= 0) {
			throw new BusinessException(ResultEnum.NO_USER_ID);
		}
		sysUserService.deleteSysUser(ids);
		return ResultVoUtil.success();
	}

	@PutMapping
	@ApiOperation("修改密码,要先调用发生验证码的接口")
	public ResultVo<SysUser> updateSysUser(@Valid @RequestBody SysUserUpdateDto sysUserDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			log.error("用户修改密码参数错误:{}", errors);
			return ResultVoUtil.error(1, errors);
		}
		if (!StringUtil.ValidateMobile(sysUserDto.getMobile())) {
			throw new BusinessException(ResultEnum.MOBILE_ERROR);
		}
		String json = redisUtils.get(Constants.MOBILE_REDIS_KEY + sysUserDto.getMobile());
		if (StringUtils.isBlank(json)) {
			throw new BusinessException(ResultEnum.CODE_NOT_EXIST);
		}
		ValidateCode code = JSONObject.parseObject(json, ValidateCode.class);
		if (!sysUserDto.getCode().equals(code.getCode())) {
			throw new BusinessException(ResultEnum.VALIDATECODE_ERROR);
		}
		log.info("修改用户密码,用户手机哈:{}", sysUserDto.getMobile());
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(sysUserDto, sysUser);
		sysUserService.updatePassword(sysUser);
		return ResultVoUtil.success();
	}

	@PutMapping("/info")
	@ApiOperation("修改用户信息")
	public ResultVo<SysUser> updateSysUserDept(@Valid @RequestBody SysUpdateDto sysUserDto, BindingResult result,
			@RequestHeader String token) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			log.error("修改用户信息参数错误:{}", errors);
			return ResultVoUtil.error(1, errors);
		}
		log.error("修改用户信息,用户id:{}", sysUserDto.getId());
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(sysUserDto, sysUser);

		sysUserService.updateSysUserInfo(sysUser, sysUserDto.getRoleIds(), token);

		return ResultVoUtil.success();
	}

	@GetMapping("/role/{id}")
	@ApiOperation("获取用户对应的角色")
	public ResultVo<List<SysRole>> getUserRole(@PathVariable String id) {
		log.error("获取用户对应的角色,用户id:{},部门id:{}", id);
		List<SysRole> userRole = sysRoleService.getUserRole(id);
		return ResultVoUtil.success(userRole);
	}

	/**
	 * 获取当前用户信息 解析jwt的token
	 */
	@GetMapping
	@ApiOperation("获取当前用户信息")
	// @ApiImplicitParams({
	// @ApiImplicitParam(name = "token", value = "认证token", required = true,
	// dataType = "string", paramType = "header", defaultValue = "username") })
	public ResultVo<SysUser> getCurrentUser(@RequestHeader("token") String token) {

		log.error("获取当前用户信息,用户token:{}", token);

		SysUser user = sysUserService.loadUserInfoByUserId(token);
		
		return ResultVoUtil.success(user);

	}

	@GetMapping("/menutree")
	@ApiOperation("获取当前用户的菜单树")
	public ResultVo<JSONArray> getUserMenuTree(@RequestHeader("token") String token) {

		log.error("获取当前用户菜单树,用户token:{}", token);

		List<SysMenu> list = sysMenuService.selectByUserId(token);
		
		JSONArray treeMenuList = MenuTreeUtils.treeMenuList(list, -1);
		return ResultVoUtil.success(treeMenuList);
	}
	
	@PostMapping("/friends/{userId}")
	@ApiOperation("添加好友")
	public ResultVo<JSONArray> addFriends(@RequestHeader("token") String token,@PathVariable String userId) {
		redisUtils.sadd(Constants.LOGIN_KEFU_FRIENDS + token, userId);		return ResultVoUtil.success();
	}
	
	@GetMapping("/friends")
	@ApiOperation("获取好友")
	public ResultVo<List<User>> getFriends(@RequestHeader("token") String token) {
		Set<String> sget = redisUtils.sget(Constants.LOGIN_KEFU_FRIENDS + token);
		List<User> list = null;
		if(sget.isEmpty()) {
			return ResultVoUtil.success(list);
		}
		list = userService.selectByIds(sget);
		return ResultVoUtil.success(list);
	}
}
