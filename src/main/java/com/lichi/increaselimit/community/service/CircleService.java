package com.lichi.increaselimit.community.service;

import org.springframework.data.domain.Page;

import com.lichi.increaselimit.community.entity.Circle;

/**
 * @author by majie on 2017/11/15.
 */
public interface CircleService {

    /**
     * 根据id查询圈子
     * @param id
     * @return
     */
    Circle get(Integer id);

    /**
     * 分页查询,并根据时间排序
     * @param page
     * @param size
     * @return
     */
    Page<Circle> getByPage(Integer page, Integer size);

    /**
     * 新增圈子
     * @param circle
     */
    void add(Circle circle);

    /**
     * 更新圈子
     * @param circle
     */
    void update(Circle circle);

    /**
     * 根据id删除圈子
     * @param id
     */
    void delete(Integer id);

}
