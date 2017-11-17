package com.lichi.increaselimit.common.exception;

import com.lichi.increaselimit.common.enums.ResultEnum;

/**
 * 业务异常
 * @author majie
 *
 */
public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private Integer code;

    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
