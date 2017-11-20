package com.lichi.increaselimit.community.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        return circleDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Circle> getByPage(Integer page, Integer size) {
    	PageHelper.startPage(page, size);
        List<Circle> list = circleDao.selectAll();
        if(list == null) {
        	return null;
        }
        for (Circle circle : list) {
            Integer count = articleDao.countByCircleId(circle.getId());
            circle.setCount(count);
		}
        PageInfo<Circle> pageInfo = new PageInfo<Circle>(list);
        return pageInfo;
    }

    @Override
    public void add(Circle circle) {
        circle.setCreateTime(new Date());
        circleDao.insert(circle);
    }

    @Override
    public void update(Circle circle) {
        circle.setUpdateTime(new Date());
        circleDao.updateByPrimaryKeySelective(circle);
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
        circleDao.deleteByPrimaryKey(id);
    }
}
