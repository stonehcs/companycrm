package com.lichi.increaselimit.community.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lichi.increaselimit.community.entity.Article;

/**
 * @author by majie on 2017/11/15.
 */
public interface ArticleDao extends JpaRepository<Article,Integer>{
    /**
     * 通过圈子id查找对应的帖子
     * @param id
     * @return
     */
    List<Article> findByCircleId(Integer id);

    /**
     * 查询该circleId下所有帖子的数目
     * @param circleId
     * @return
     */
    Integer countByCircleId(Integer circleId);
}
