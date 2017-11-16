package com.lichi.increaselimit.community.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.CircleException;
import com.lichi.increaselimit.community.dao.ArticleDao;
import com.lichi.increaselimit.community.dao.CircleDao;
import com.lichi.increaselimit.community.entity.Article;
import com.lichi.increaselimit.community.entity.Circle;
import com.lichi.increaselimit.community.service.CircleService;

/**
 * @author by majie on 2017/11/15.
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class CircleImpl implements CircleService {

    @Autowired
    private CircleDao circleDao;

    @Autowired
    private ArticleDao articleDao;

    @Override
    public Circle get(Integer id) {

        return circleDao.findOne(id);
    }

    @Override
    public Page<Circle> getByPage(Integer page, Integer size) {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"createTime");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(page,size,sort);
        Page<Circle> all = circleDao.findAll(pageable);
        return all;
    }

    @Override
    public void add(Circle circle) {
        circle.setCreateTime(new Date());
        circleDao.save(circle);
    }

    @Override
    public void update(Circle circle) {
        circle.setUpdateTime(new Date());
        circleDao.save(circle);
    }

    @Override
    public void delete(Integer id) {
        List<Article> list = articleDao.findByCircleId(id);
        if(list != null && list.size() > 0){
            throw new CircleException(ResultEnum.ARTICLE_NO_EMPTY);
        }
        circleDao.delete(id);
    }
}
