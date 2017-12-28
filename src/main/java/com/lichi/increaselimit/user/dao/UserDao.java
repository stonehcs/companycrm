package com.lichi.increaselimit.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lichi.increaselimit.common.mapper.BaseMapper;
import com.lichi.increaselimit.user.entity.User;
import com.lichi.increaselimit.user.entity.UserShare;
import com.lichi.increaselimit.user.entity.UserVo;

/**
 * userdao
 * @author majie
 *
 */
@Mapper
public interface UserDao extends BaseMapper<User>{
	
	/**
	 * 获取用户信息
	 * @param username
	 * @return
	 */
	@Select("select * from t_user where mobile=#{mobile}")
	User loadUserInfoByMobile(String mobile);

	@Select("select a.*,b.nickname as invitationtor,c.* from t_user a left join t_user b on a.pid = b.id "
			+ "left join t_vip_level c on a.vip_level = c.id ")
	List<UserVo> selectAllUser();
	
	@Select("select a.*,b.nickname as invitationtor,c.* from t_user a left join t_user b on a.pid = b.id "
			+ "left join t_vip_level c on a.vip_level = c.id "
			+ "where a.nickname LIKE concat('%', #{key}, '%') "
			+ "or a.mobile LIKE concat('%', #{key}, '%')")
	List<UserVo> selectAllLike(String key);

	/**
	 * 根据pid查询下级用户
	 * @param pid
	 * @return
	 */
	@Select("select a.*,b.nickname as invitationtor,c.* from t_user a left join t_user b on a.pid = b.id "
			+ "left join t_vip_level c on a.vip_level = c.id where a.pid = #{userId}")
	List<UserVo> getAllShare(String userId);
	
	/**
	 * 根据pid查询
	 * @param pid
	 * @return
	 */
	@Select("select a.*,b.nickname as invitationtor,c.* from t_user a left join t_user b on a.pid = b.id "
			+ "left join t_vip_level c on a.vip_level = c.id where a.id = #{pid}")
	UserVo selectByPid(String pid);

	/**
	 * 更新上级用户邀请人数
	 * @param pid
	 */
	@Update("update t_user set rank = rank + 1 where id = #{pid}")
	void updatePidRank(String pid);

	
	@Select("select count(a.id) from t_user a left join t_user b on a.pid = b.id "
			+ "left join t_vip_level c on a.vip_level = c.id where a.pid = #{userId}")
	Integer selectShareCount(String userId);
	
	@Select("select a.id,a.head_img,a.nickname from t_user a left join t_user b on a.pid = b.id "
			+ "left join t_vip_level c on a.vip_level = c.id where a.pid = #{userId} limit 4")
	List<UserShare> selectShareUser(String userId);

	@Select("select a.*,b.nickname as invitationtor,c.* from t_user a left join t_user b on a.pid = b.id "
			+ "left join t_vip_level c on a.vip_level = c.id where a.pid = #{userId}"
			+ "and a.nickname LIKE concat('%', #{key}, '%') or a.mobile LIKE concat('%', #{key}, '%')")
	List<UserVo> getAllShareLike(@Param(value = "userId") String userId, @Param(value = "key") String key);
}
