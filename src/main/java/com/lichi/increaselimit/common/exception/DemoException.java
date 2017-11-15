package com.lichi.increaselimit.common.exception;

import com.lichi.increaselimit.common.enums.ResultEnum;

/**
 * 异常举例
 * @author majie
 *
 */
public class DemoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private Integer code;

    public DemoException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public DemoException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
