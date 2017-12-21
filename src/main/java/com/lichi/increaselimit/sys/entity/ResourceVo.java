package com.lichi.increaselimit.sys.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceVo {
	
	private String url;
	
	private String method;

	public ResourceVo(String url, String method) {
		super();
		this.url = url;
		this.method = method;
	}
	
	
}
