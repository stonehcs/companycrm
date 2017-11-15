package com.lichi.increaselimit.common.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lichi.increaselimit.common.exception.DemoException;

/**
 * 处理demoExcetpion
 * @author majie
 *
 */
@RestControllerAdvice
public class DemoExceptionHandler {
	
	@ExceptionHandler(DemoException.class)
	public void hadler(){
		
	}
}
