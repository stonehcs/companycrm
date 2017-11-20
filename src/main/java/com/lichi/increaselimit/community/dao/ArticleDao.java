package com.lichi.increaselimit.community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lichi.increaselimit.common.config.mybatis.BaseDao;
import com.lichi.increaselimit.community.entity.Article;


/**
 * @author by majie on 2017/11/15.
 */
@Mapper
public interface ArticleDao extends BaseDao<Article>{
	
	/**
	 * 新增帖子
	 * @param article
	 * @return 
	 */
//	@Insert("insert into t_article (id, circle_id, create_time, create_user_id, title, update_time, content)"
//			+ "values(#{id}, #{circleId}, #{createTime},  #{createUserId}, #{title}, #{updateTime},#{content})")
//	int insert(Article article);

	/**
	 * 删除帖子
	 * @param id
	 */
//	@Delete("delete from t_article where id = #{id}")
//	void deleteByPrimaryKey(Integer id);
	
	/**
	 * 修改帖子
	 * @param article
	 * @return 
	 */
//	int updateByPrimaryKeySelective(Article article);
	
	/**
	 * 根据主键
	 * @param id
	 * @return
	 */
//	@Select("select * from t_article where id = #{id}")
//	Article selectByPrimaryKey(Integer id);
	
	/**
	 * 查询圈子下所有帖子
	 * @param circleId
	 * @return
	 */
	@Select("select * from t_article where circle_id = #{circleId}")
	List<Article> selectByCircleId(Integer circleId);
	
	/**
	 * 查询所有
	 * @return
	 */
//	@Select("select * from t_article")
//	List<Article> selectAll();
	
	
	/**
	 * 根据circleId获取所有帖子数量
	 * @param circleId
	 * @return
	 */
	@Select("select count(id) from t_article where circle_id = #{circleId}")
	Integer countByCircleId(Integer circleId);

}
