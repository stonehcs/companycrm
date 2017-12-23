package com.lichi.increaselimit.common.utils;

import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.lichi.increaselimit.common.Constants;
import com.lichi.increaselimit.sys.entity.SysUser;

/**
 * 生成用户id
 * 
 * @author majie
 *
 */
public class IdUtils {

	public static SnowflakeIdWorker SNOWFLAKEIDWORKER = new SnowflakeIdWorker(0, 0);

	/**
	 * 获取用户id
	 * 
	 * @return
	 */
	public static String getUserId() {
		return SNOWFLAKEIDWORKER.nextId() + "";
	}
	
	/**
	 * 获取uuid
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 获取登录用户id
	 * @return
	 */
	public static String getUserId(RedisUtils redisUtils,String token) {
		String json = redisUtils.get(Constants.LOGIN_SYS_USER + token);
		SysUser sysUser = JSONObject.parseObject(json, SysUser.class);
		return sysUser.getId();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(getUserId());
		}
	}
}
