package com.lichi.increaselimit.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.sys.entity.Role;

public interface RoleService {

	PageInfo<Role> selectAll(Integer page, Integer size);

	List<Role> selectList();

	void insertOne(Role Role);

	void update(Role Role);

	Role selectOne(Integer id);

	void deleteOne(Integer id);

}
