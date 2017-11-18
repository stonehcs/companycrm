package com.lichi.increaselimit.community.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.community.dao.ArticleDao;
import com.lichi.increaselimit.community.dao.CircleDao;
import com.lichi.increaselimit.community.entity.Article;
import com.lichi.increaselimit.community.entity.Circle;
import com.lichi.increaselimit.community.service.ArticleService;

/**
 * @author by majie on 2017/11/15.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleImpl implements ArticleService{

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private CircleDao circleDao;

    @Override
    public Article get(Integer id) {

        return articleDao.findOne(id);
    }

    @Override
    public Page<Article> getByPage(Integer page, Integer size,Integer circleId) {
        Pageable pageable = new PageRequest(page,size);
        Page<Article> all = articleDao.findByCircleIdOrderByCreateTimeDesc(pageable,circleId);
        return all;
    }
    
    /**
     * 先查询圈子是否存在,不然不能发帖
     */
    @Override
    public void add(Article article) {
        Circle circle = circleDao.findOne(article.getCircleId());
        if(circle == null){
            throw new BusinessException(ResultEnum.CIRCLE_NO_EXIST);
        }
        article.setCreateTime(new Date());
        articleDao.save(article);
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(new Date());
        articleDao.save(article);
    }

    @Override
    public void delete(Integer id) {

        articleDao.delete(id);
    }

    @Override
    public Integer getCountByCircleId(Integer articleId){
        return articleDao.countByCircleId(articleId);
    }
}
