package com.lichi.increaselimit.common.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.vo.ResultVo;
import com.lichi.increaselimit.security.validate.code.ValidateCodeException;

import lombok.extern.slf4j.Slf4j;

/**
 * 异常处理
 * @author majie
 *
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResultVo<?> handler(Exception e){
		if(e instanceof BusinessException){
			BusinessException validateException = (BusinessException)e;
			return ResultVoUtil.error(validateException.getCode(),validateException.getMessage());
		}
		if(e instanceof ValidateCodeException) {
			return ResultVoUtil.error(401, e.getMessage());
		}
		e.printStackTrace();
		log.error(e.getMessage());
		return ResultVoUtil.error(1,"系统异常");
	}
}
