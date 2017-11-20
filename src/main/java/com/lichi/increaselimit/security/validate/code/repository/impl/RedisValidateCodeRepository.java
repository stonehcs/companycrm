package com.lichi.increaselimit.security.validate.code.repository.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import com.alibaba.fastjson.JSONObject;
import com.lichi.increaselimit.common.utils.RedisUtils;
import com.lichi.increaselimit.security.validate.code.ValidateCode;
import com.lichi.increaselimit.security.validate.code.ValidateCodeException;
import com.lichi.increaselimit.security.validate.code.ValidateCodeType;
import com.lichi.increaselimit.security.validate.code.repository.ValidateCodeRepository;

/**
 * 验证码的存放使用redis
 * 
 * @author majie
 * @param <T>
 *
 */
@Component
public class RedisValidateCodeRepository<T> implements ValidateCodeRepository {
	
	@Autowired
	private RedisUtils redisUtils;

	@Override
	public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
		redisUtils.set(buildKey(request, type), JSONObject.toJSONString(code), 60);
	}

	@Override
	public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
		Object value = redisUtils.get(buildKey(request, type));
		if (value == null) {
			return null;
		}
		return JSONObject.parseObject(value + "", ValidateCode.class);
	}

	@Override
	public void remove(ServletWebRequest request, ValidateCodeType type) {
		redisUtils.del(buildKey(request, type));
	}

	private String buildKey(ServletWebRequest request, ValidateCodeType type) {
		String deviceId = request.getHeader("deviceId");
		if (StringUtils.isBlank(deviceId)) {
			throw new ValidateCodeException("请在请求头中携带deviceId参数");
		}
		return "code:" + type.toString().toLowerCase() + ":" + deviceId;
	}
}
