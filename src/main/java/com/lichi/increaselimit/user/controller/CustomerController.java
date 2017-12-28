package com.lichi.increaselimit.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.Constants;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.common.utils.RedisUtils;
import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.utils.StringUtil;
import com.lichi.increaselimit.common.vo.ResultVo;
import com.lichi.increaselimit.security.validate.code.ValidateCode;
import com.lichi.increaselimit.user.controller.dto.RegistrDto;
import com.lichi.increaselimit.user.entity.UserVo;
import com.lichi.increaselimit.user.entity.VipLevel;
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
	
	@Autowired
	private RedisUtils redisUtils;

	@GetMapping
	@ApiOperation("分页查询所有客户")
	public ResultVo<PageInfo<UserVo>> getAll(
			@ApiParam(value = "页码", required = false) @RequestParam(defaultValue = "1", required = false) Integer page,
			@ApiParam(value = "条数", required = false) @RequestParam(defaultValue = "20", required = false) Integer size,
			@RequestParam(required = false) String key,
			@RequestParam(required = false) String userId) {
		log.info("分页查询所有客户");
		PageInfo<UserVo> list = userService.selectAll(page, size ,key,userId);
		return ResultVoUtil.success(list);
	}
	
	@PostMapping
	@ApiOperation("客户注册")
	public ResultVo<Object> register(@Valid @RequestBody RegistrDto registrDto,BindingResult result) {
		if (result.hasErrors()) {
			String errors = result.getFieldError().getDefaultMessage();
			log.error("客户注册参数错误:{}" + errors);
			return ResultVoUtil.error(1, errors);
		}
		String mobile = registrDto.getMobile();
		log.info("客户注册,手机号:{}",mobile);
		if(!StringUtil.ValidateMobile(mobile)) {
			throw new BusinessException(ResultEnum.MOBILE_ERROR);
		}
		String codejson = redisUtils.get(Constants.MOBILE_REDIS_KEY + mobile);
		if(StringUtils.isBlank(codejson)) {
			throw new BusinessException(ResultEnum.VALIDATECODE_TIMEOUT);
		}
		ValidateCode validateCode = JSONObject.parseObject(codejson, ValidateCode.class);
		if(!registrDto.getCode().equals(validateCode.getCode())) {
			throw new BusinessException(ResultEnum.VALIDATECODE_ERROR);
		}
		userService.insertCustomer(mobile,registrDto.getPid());
		return ResultVoUtil.success();
	}
	
	@GetMapping("/up/{pid}")
	@ApiOperation("查询选中客户的上层关系")
	public ResultVo<UserVo> getUp(@PathVariable String pid) {
		log.info("分页查询所有客户");
		UserVo list = userService.selectByPid(pid);
		return ResultVoUtil.success(list);
	}
	
	@GetMapping("/level")
	@ApiOperation("获取用户等级")
	public ResultVo<List<VipLevel>> getLevel() {
		log.info("获取用户等级");
		List<VipLevel> list = userService.selectLevel();
		return ResultVoUtil.success(list);
	}
	
	@PutMapping
	@ApiOperation("修改客户等级")
	public Object update(@RequestParam(required = true) String userId,@RequestParam(required = true) Integer levelId) {
		log.info("修改客户等级,用户id:{},修改的等级id:{}",userId,levelId);
		userService.updateUserInfo(userId,levelId);
		return ResultVoUtil.success();
	}
}
