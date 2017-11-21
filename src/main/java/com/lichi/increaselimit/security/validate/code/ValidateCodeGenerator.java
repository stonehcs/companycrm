package com.lichi.increaselimit.security.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 生成验证接口
 * @author majie
 *
 */
public interface ValidateCodeGenerator {
	
	/**
	 * 生产验证码
	 * @param request
	 * @return
	 */
	ValidateCode generate(ServletWebRequest request);
	
}
