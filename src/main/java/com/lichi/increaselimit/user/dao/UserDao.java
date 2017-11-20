package com.lichi.increaselimit.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lichi.increaselimit.common.config.mybatis.BaseDao;
import com.lichi.increaselimit.user.entity.User;

/**
 * userdao
 * @author majie
 *
 */
@Mapper
public interface UserDao extends BaseDao<User>{
	
	@Select("select * from t_user where username=#{username} or mobile=#{username}"
			+ "or qq=#{username} or weixin=#{username}")
	User loadUserInfo(String username);
}
