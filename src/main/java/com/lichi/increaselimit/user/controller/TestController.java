package com.lichi.increaselimit.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 测试controller
 * @author majie
 *
 */
@Api(value = "/user")
@RestController
public class TestController {
	
	@ApiOperation(value = "方法注解")
	@GetMapping("index")
	public Object index(){
		return "hello lichi";
	}
}
