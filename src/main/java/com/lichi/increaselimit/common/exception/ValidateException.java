package com.lichi.increaselimit.common.exception;

import com.lichi.increaselimit.common.enums.ResultEnum;

/**
 * 参数校验错误
 * @author majie
 *
 */
public class ValidateException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private Integer code;

    public ValidateException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public ValidateException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
