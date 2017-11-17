package com.lichi.increaselimit.community.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lichi.increaselimit.community.entity.Circle;

/**
 * @author by majie on 2017/11/15.
 */
public interface CircleDao extends JpaRepository<Circle,Integer>{
	
	/**
	 * 查询所有根据创建时间倒序排列
	 * @param pageable
	 * @return
	 */
	Page<Circle> findAllByOrderByCreateTimeDesc(Pageable pageable);
}
