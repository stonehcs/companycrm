package com.lichi.increaselimit.community.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.lichi.increaselimit.common.config.mybatis.BaseDao;
import com.lichi.increaselimit.community.entity.Circle;

/**
 * @author by majie on 2017/11/15.
 */
@Mapper
public interface CircleDao extends BaseDao<Circle>{
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
//	@Select("select * from t_circle where id = #{id}")
//	Circle selectByPrimaryKey(Integer id);
	
	/**
	 * 查询所有
	 * @return 
	 */
//	@Select("select * from t_circle")
//	List<Circle> selectAll();
	
	/**
	 * 根据id删除
	 * @param id
	 */
//	@Delete("delete from t_circle where id = #{id}")
//	void deleteByPrimaryKey(Integer id);
	
	/**
	 * 插入
	 * @param circle
	 * @return 
	 */
//	@Insert("insert into t_circle (id,create_time, create_user_id,name, update_time, url)"
//			+ "values(#{id}, #{createTime},#{createUserId},#{name},#{updateTime}, #{url})")
//	int insert(Circle circle);
	
	/**
	 * 更新
	 * @param circle
	 * @return 
	 */
//	int updateByPrimaryKeySelective(Circle circle);

	/**
	 * 获得所有条数
	 * @return
	 */
	@Select("select count(id) from t_circle")
	int getAllCount();
	
}
