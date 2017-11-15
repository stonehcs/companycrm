package com.lichi.increaselimit.common.utils;

import com.lichi.increaselimit.common.vo.ResultVo;

/**
 * resultUtil
 * @author majie
 *
 */
public class ResultVoUtil {
	
	/**
	 * 成功 + 数据
	 * @param object
	 * @return
	 */
	public static <T> ResultVo<T> success(T object) {
		ResultVo<T> resultvo = new ResultVo<T>();
        resultvo.setData(object);
        resultvo.setCode(0);
        resultvo.setMsg("成功");
        return resultvo;
    }
	
	/**
	 * 成功 
	 * @return
	 */
    public static <T> ResultVo<T> success() {
        return success(null);
    }
    
    /**
     * 失败码 + 信息
     * @param code
     * @param msg
     * @return
     */
    public static <T> ResultVo<T> error(Integer code, String msg) {
    	ResultVo<T> resultvo = new ResultVo<T>();
        resultvo.setCode(code);
        resultvo.setMsg(msg);
        return resultvo;
    }
}
