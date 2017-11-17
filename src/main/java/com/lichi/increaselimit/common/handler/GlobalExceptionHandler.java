package com.lichi.increaselimit.common.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.common.utils.ResultVoUtil;
import com.lichi.increaselimit.common.vo.ResultVo;

/**
 * 异常处理
 * @author majie
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResultVo<?> handler(Exception e){
		if(e instanceof BusinessException){
			BusinessException validateException = (BusinessException)e;
			return ResultVoUtil.error(validateException.getCode(),validateException.getMessage());
		}
		return ResultVoUtil.error(0,"系统异常");
	}
}
