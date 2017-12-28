package com.lichi.increaselimit.common.utils;

import java.util.UUID;

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

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(getUserId());
		}
	}
}
