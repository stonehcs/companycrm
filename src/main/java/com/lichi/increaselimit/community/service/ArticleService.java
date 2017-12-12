package com.lichi.increaselimit.community.service;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.community.entity.Article;
import com.lichi.increaselimit.community.entity.ArticleVo;
import com.lichi.increaselimit.community.entity.CircleVo;

/**
 * @author by majie on 2017/11/15.
 */
public interface ArticleService {

    /**
     * 根据帖子id查询帖子
     * @param id
     * @return
     */
    ArticleVo get(Integer id);

    /**
     * 分页查询当前圈子下的热门帖子
     * @param page
     * @param size
     * @param circleId 
     * @return
     */
    PageInfo<ArticleVo> getByPage(Integer page, Integer size, Integer circleId);

    /**
     * 发帖子
     * @param article
     */
    void add(Article article);

    /**
     * 更新帖子
     * @param article
     */
    void update(Article article);

    /**
     * 根据id删除帖子
     * @param id
     */
    void delete(Integer id);

    /**
     * 分页查询热门帖子
     * @param page
     * @param size
     * @return
     */
	PageInfo<ArticleVo> getHotByPage(Integer page, Integer size);
	
	/**
	 * 模糊查询
	 * @param page
	 * @param size
	 * @param name
	 * @return
	 */
	PageInfo<CircleVo> seleteByLike(Integer page, Integer size, String name);
}
