package com.lichi.increaselimit.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lichi.increaselimit.common.mapper.BaseMapper;
import com.lichi.increaselimit.user.entity.User;
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
			+ "left join t_vip_level c on a.vip_level = c.level")
	List<UserVo> selectAllUser();

	/**
	 * 根据pid查询
	 * @param pid
	 * @return
	 */
	@Select("select a.*,b.nickname as invitationtor from t_user a left join t_user b on a.pid = b.id where a.id = #{pid}")
	UserVo selectByPid(String pid);

	/**
	 * 更新上级用户邀请人数
	 * @param pid
	 */
	@Update("update t_user set rank = rank + 1 where id = #{pid}")
	void updatePidRank(String pid);
}
