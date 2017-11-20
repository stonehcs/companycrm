package com.lichi.increaselimit.common.config.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用dao
 * @author majie
 *
 * @param <T>
 */
public interface BaseDao<T> extends Mapper<T>,MySqlMapper<T>{

}

