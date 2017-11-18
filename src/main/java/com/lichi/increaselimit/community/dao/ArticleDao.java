package com.lichi.increaselimit.community.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lichi.increaselimit.community.entity.Article;

/**
 * @author by majie on 2017/11/15.
 */
public interface ArticleDao extends JpaRepository<Article,Integer>{

    /**
     * 查询该circleId下所有帖子的数目
     * @param circleId
     * @return
     */
    Integer countByCircleId(Integer circleId);
    
    /**
     * 根据圈子id查询所有帖子
     * 根据时间倒序排列
     * @param pageable
     * @param circleId
     * @return
     */
	Page<Article> findByCircleIdOrderByCreateTimeDesc(Pageable pageable,Integer circleId);
}
