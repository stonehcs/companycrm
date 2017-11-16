package com.lichi.increaselimit.community.service.impl;

import com.lichi.increaselimit.community.dao.ArticleDao;
import com.lichi.increaselimit.community.entity.Article;
import com.lichi.increaselimit.community.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by majie on 2017/11/15.
 */
@Service
public class ArticleImpl implements ArticleService{

    @Autowired
    private ArticleDao articleDao;

    @Override
    public Article get(Integer id) {

        return articleDao.findOne(id);
    }

    @Override
    public Page<Article> getByPage(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page,size);
        Page<Article> all = articleDao.findAll(pageable);
        return all;
    }

    @Override
    public void add(Article article) {
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
