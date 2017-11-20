/**
 * 
 */
package com.lichi.increaselimit.security.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;


/**
 * 验证码父类
 * @author majie
 *
 */
@Data
public class ValidateCode implements Serializable{
	
	private static final long serialVersionUID = -967751629001832755L;

	private String code;
	
	private LocalDateTime expireTime;
	
	public ValidateCode(String code, int expireIn){
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}
	
	public ValidateCode(String code, LocalDateTime expireTime){
		this.code = code;
		this.expireTime = expireTime;
	}
	
	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}

	public ValidateCode() {
		super();
	}

	
}
