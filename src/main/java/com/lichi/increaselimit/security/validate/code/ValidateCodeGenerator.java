package com.lichi.increaselimit.security.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 生成验证接口
 * @author majie
 *
 */
public interface ValidateCodeGenerator {

	ValidateCode generate(ServletWebRequest request);
	
}
