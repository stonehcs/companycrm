package com.lichi.increaselimit.common.exception;

import com.lichi.increaselimit.common.enums.ResultEnum;

/**
 * 圈子异常
 * @author majie
 *
 */
public class CircleException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private Integer code;

    public CircleException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public CircleException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
