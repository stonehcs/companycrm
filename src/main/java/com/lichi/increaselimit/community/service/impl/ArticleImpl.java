package com.lichi.increaselimit.community.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.common.utils.StringUtil;
import com.lichi.increaselimit.community.dao.ArticleDao;
import com.lichi.increaselimit.community.dao.CircleDao;
import com.lichi.increaselimit.community.entity.Article;
import com.lichi.increaselimit.community.entity.ArticleVo;
import com.lichi.increaselimit.community.entity.Circle;
import com.lichi.increaselimit.community.service.ArticleService;

/**
 * @author by majie on 2017/11/15.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private CircleDao circleDao;

	@Override
	public ArticleVo get(Integer id) {

		return articleDao.selectById(id);
	}

	@Override
	public PageInfo<ArticleVo> getByPage(Integer page, Integer size, Integer circleId) {
		PageHelper.startPage(page, size);
		PageHelper.orderBy("create_time desc");
		List<ArticleVo> list = articleDao.selectByCircleId(circleId);
		PageInfo<ArticleVo> pageInfo = new PageInfo<ArticleVo>(list);
		return pageInfo;
	}

	/**
	 * 先查询圈子是否存在,不然不能发帖
	 * 并获取摘要
	 * 
	 */
	@Override
	public void add(Article article) {
		Circle circle = circleDao.selectByPrimaryKey(article.getCircleId());
		if (circle == null) {
			throw new BusinessException(ResultEnum.CIRCLE_NO_EXIST);
		}
		article.setCreateTime(new Date());
		article.setUpdateTime(new Date());
		String content = article.getContent();
		article.setSummary(StringUtil.getContentWord(content));
		articleDao.insertSelective(article);
	}

	@Override
	public void update(Article article) {
		if(StringUtils.isBlank(article.getContent())) {
			String content = article.getContent();
			article.setSummary(StringUtil.getContentWord(content));
		}
		article.setUpdateTime(new Date());
		articleDao.updateByPrimaryKeySelective(article);
	}

	@Override
	public void delete(Integer id) {
		articleDao.deleteByPrimaryKey(id);
	}


	@Override
	public PageInfo<ArticleVo> getHotByPage(Integer page, Integer size) {
		PageHelper.startPage(page, size);
		PageHelper.orderBy("sort desc,create_time desc");
		List<ArticleVo> list = articleDao.selectHot();
		PageInfo<ArticleVo> pageInfo = new PageInfo<ArticleVo>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<ArticleVo> seleteByLike(Integer page, Integer size, String name,Integer circleId) {
    	PageHelper.startPage(page, size);
    	PageHelper.orderBy("create_time desc");
    	List<ArticleVo> list = articleDao.selectListLike(name,circleId);
    	PageInfo<ArticleVo> pageInfo = new PageInfo<ArticleVo>(list);
		return pageInfo;
	}
}
