package com.lichi.increaselimit.security.validate.code.repository;

import org.springframework.web.context.request.ServletWebRequest;

import com.lichi.increaselimit.security.validate.code.ValidateCode;
import com.lichi.increaselimit.security.validate.code.ValidateCodeType;

/**
 * 验证码保存接口
 * @author majie
 *
 */
public interface ValidateCodeRepository {

	/**
	 * 保存验证码
	 * @param request
	 * @param code
	 * @param validateCodeType
	 */
	void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

	/**
	 * 获取验证码
	 * @param request
	 * @param validateCodeType
	 * @return
	 */
	ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);
	
	/**
	 * 移除验证码
	 * @param request
	 * @param validateCodeType
	 */
	void remove(ServletWebRequest request, ValidateCodeType validateCodeType);
}
