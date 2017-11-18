package com.lichi.increaselimit.community.service.impl;

import java.util.Date;
import java.util.List;

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
import com.lichi.increaselimit.community.entity.Circle;
import com.lichi.increaselimit.community.service.CircleService;

/**
 * @author by majie on 2017/11/15.
 */
@Service
@Transactional(rollbackFor = Exception.class)
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
        Pageable pageable = new PageRequest(page,size);
        Page<Circle> pages = circleDao.findAllByOrderByCreateTimeDesc(pageable);
        if(pages == null) {
        	return null;
        }
        List<Circle> content = pages.getContent();
        
//        content.stream().forEach(e -> e.setCount(articleDao.countByCircleId(e.getId())));
        for (Circle circle : content) {
            Integer count = articleDao.countByCircleId(circle.getId());
            circle.setCount(count);
		}
        return pages;
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
    
    /**
     * 删除的时候先查询是否有帖子
     */
    @Override
    public void delete(Integer id) {
        Integer resutl = articleDao.countByCircleId(id);
        if(resutl > 0){
            throw new BusinessException(ResultEnum.ARTICLE_NO_EMPTY);
        }
        circleDao.delete(id);
    }
}
