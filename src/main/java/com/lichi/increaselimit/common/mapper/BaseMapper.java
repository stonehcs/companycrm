package com.lichi.increaselimit.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用dao
 * @author majie
 *
 * @param <T>
 */
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T>{

}

