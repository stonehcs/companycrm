package com.lichi.increaselimit.user.controller;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

import com.alibaba.fastjson.JSONObject;
import com.lichi.increaselimit.common.Constants;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.common.utils.RedisUtils;
import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.utils.StringUtil;
import com.lichi.increaselimit.common.vo.ResultVo;
import com.lichi.increaselimit.security.properties.SecurityProperties;
import com.lichi.increaselimit.security.validate.code.ValidateCode;
import com.lichi.increaselimit.user.controller.dto.SysUserDto;
import com.lichi.increaselimit.user.entity.SysUser;
import com.lichi.increaselimit.user.service.SysUserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 系统用户信息controller
 * 
 * @author majie
 *
 */
@Api(description = "系统用户信息")
@RestController
@RequestMapping("/sysuser")
public class SysUserController {

	@Autowired
	private SecurityProperties systemProperties;

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private SysUserService sysUserService;

	@PostMapping("/regiter")
	@ApiOperation("注册，调用此接口请先调用发送验证码的接口")
	public ResultVo<SysUser> register(@Valid @RequestBody SysUserDto sysUserDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			return ResultVoUtil.error(1, errors);
		}
		if(!StringUtil.ValidateMobile(sysUserDto.getMobile())) {
			throw new BusinessException(ResultEnum.MOBILE_ERROR);
		}
		String json = redisUtils.get(Constants.MOBILE_REDIS_KEY + sysUserDto.getMobile());
		
		ValidateCode code = JSONObject.parseObject(json, ValidateCode.class);
		if (!sysUserDto.getCode().equals(code.getCode())) {
			throw new BusinessException(ResultEnum.VALIDATECODE_ERROR);
		}

		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(sysUserDto, sysUser);
		sysUserService.insertUser(sysUser);
		return ResultVoUtil.success();
	}

	@GetMapping("/all")
	@ApiOperation("分页查询所有用户")
	public ResultVo<List<SysUser>> getAll(
			@ApiParam(value = "页码", required = false) @RequestParam(defaultValue = "1", required = false) Integer page,
			@ApiParam(value = "条数", required = false) @RequestParam(defaultValue = "20", required = false) Integer size) {
		List<SysUser> list = sysUserService.selectAll(page,size);
		return ResultVoUtil.success(list);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("根据系统用户id删除用户信息")
	public ResultVo<SysUser> deleteSysUser(@PathVariable Integer id) {
		sysUserService.deleteSysUser(id);
		return ResultVoUtil.success();
	}
	
	@PutMapping
	@ApiOperation("修改密码,要先调用发生验证码的接口")
	public ResultVo<SysUser> updateSysUser(@Valid @RequestBody SysUserDto sysUserDto, BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			return ResultVoUtil.error(1, errors);
		}
		if(!StringUtil.ValidateMobile(sysUserDto.getMobile())) {
			throw new BusinessException(ResultEnum.MOBILE_ERROR);
		}
		String json = redisUtils.get(Constants.MOBILE_REDIS_KEY + sysUserDto.getMobile());
		ValidateCode code = JSONObject.parseObject(json, ValidateCode.class);
		if (!sysUserDto.getCode().equals(code.getCode())) {
			throw new BusinessException(ResultEnum.VALIDATECODE_ERROR);
		}
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(sysUserDto, sysUser);
		sysUserService.updateSysUser(sysUser);
		return ResultVoUtil.success();
	}
	
	

	/**
	 * 获取当前用户信息 解析jwt的token
	 */
	@GetMapping
	@ApiOperation("获取当前用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "string", paramType = "header", defaultValue = "bearer ") })
	public ResultVo<SysUser> getCurrentUser(Authentication authentication, HttpServletRequest request)
			throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException,
			IllegalArgumentException, UnsupportedEncodingException {
		String header = request.getHeader("Authorization");

		String token = StringUtils.substringAfter(header, "bearer ");

		Claims claims = Jwts.parser()
				.setSigningKey(systemProperties.getOauth2Properties().getJwtSigningKey().getBytes("UTF-8"))
				.parseClaimsJws(token).getBody();

		return ResultVoUtil.success((SysUser) claims.get("user"));
	}

}
