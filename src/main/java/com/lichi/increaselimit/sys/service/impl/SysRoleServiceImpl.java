package com.lichi.increaselimit.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.sys.controller.dto.SysRoleResourceDto;
import com.lichi.increaselimit.sys.controller.dto.SysRoleResourceVo;
import com.lichi.increaselimit.sys.dao.SysRoleDao;
import com.lichi.increaselimit.sys.dao.SysRoleResourceDao;
import com.lichi.increaselimit.sys.entity.SysRole;
import com.lichi.increaselimit.sys.entity.SysRoleResource;
import com.lichi.increaselimit.sys.service.SysRoleService;

import tk.mybatis.mapper.entity.Example;

@Service
public class SysRoleServiceImpl implements SysRoleService{
	@Autowired
	private SysRoleDao roleDao;
	@Autowired
	private SysRoleResourceDao sysRoleResourceDao;

	@Override
	public PageInfo<SysRole> selectAll(Integer page, Integer size,String name) {
		PageHelper.startPage(page, size);
		PageHelper.orderBy("create_time desc");
		List<SysRole> list = null;
		if(!StringUtils.isBlank(name)) {
			list = roleDao.selectByLike(name);
		}else{
			list = roleDao.selectAll();
		}
		PageInfo<SysRole> info = new PageInfo<>(list);
		return info;
	}

	@Override
	public SysRole selectOne(Integer id) {
		return roleDao.selectByPrimaryKey(id);
	}

	@Override
	public void insertOne(SysRole role) {
		SysRole Roleresult = roleDao.selectByName(role.getRoleName());
		if(Roleresult != null) {
			throw new BusinessException(ResultEnum.ROLE_EXIST);
		}
		role.setUpdateTime(new Date());
		role.setCreateTime(new Date());
		roleDao.insertSelective(role);
	}

	@Override
	public void delete(List<Integer> ids) {
		Example example = new Example(SysRole.class);
		example.createCriteria().andIn("id", ids);
		roleDao.deleteByExample(example);
	}

	@Override
	public void insertOrUpdate(SysRole role) {
		//id为空是新增,不为空是更新
		role.setUpdateTime(new Date());
		if(null == role.getId()) {
			role.setCreateTime(new Date());
			roleDao.insertSelective(role);
		}else {
			roleDao.updateByPrimaryKeySelective(role);
		}
		
	}

	@Override
	public List<SysRole> selectList() {
		PageHelper.orderBy("create_time desc");
		List<SysRole> list = roleDao.selectAll();
		return list;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addOrUpdate(SysRoleResourceVo vo) {
		List<SysRoleResource> resultlist = new ArrayList<>();
		
		//先更新或插入角色信息
		Integer roleId = insertOrUpdate(vo.getRoleId(),vo.getRoleName());
		
		List<SysRoleResourceDto> list = vo.getList();
		
		//删除原来的角色资源
		deleteRoleResource(roleId);
		
		//添加资源
		list.stream().forEach(e ->{
			if(-1 == e.getButtonId() && -1 == e.getMenuId()) {
				throw new BusinessException(ResultEnum.MUNE_BOTTUN_BOTH_NULL);
			}
			SysRoleResource sysRoleResource = new SysRoleResource();
			BeanUtils.copyProperties(e, sysRoleResource);
			sysRoleResource.setRoleId(roleId);
			resultlist.add(sysRoleResource);
		});
		sysRoleResourceDao.insertList(resultlist);
	}

	public Integer insertOrUpdate(Integer roleId,String roleName) {
		if(null == roleId) {
			SysRole record = new SysRole();
			record.setRoleName(roleName);
			record.setUpdateTime(new Date());
			record.setCreateTime(new Date());
			roleDao.insert(record);
			SysRole sysRole = roleDao.selectByName(roleName);
			roleId = sysRole.getId();
		}else {
			SysRole record = new SysRole();
			record.setId(roleId);
			record.setRoleName(roleName);
			record.setUpdateTime(new Date());
			SysRole sysRole = roleDao.selectByName(roleName);
			if(sysRole != null) {
				throw new BusinessException(ResultEnum.ROLE_EXIST);
			}
			roleDao.updateByPrimaryKey(record);
		}
		return roleId;
	}

	@Override
	public List<SysRoleResource> selectResource(Integer id) {
		Example example = new Example(SysRoleResource.class);
		example.createCriteria().andEqualTo("roleId",id);
		List<SysRoleResource> list = sysRoleResourceDao.selectByExample(example);
		return list;
	}

	@Override
	public List<SysRole> getUserRole(String id) {
		return roleDao.getUserRole(id);
	}

	public void deleteRoleResource(Integer roleId) {
		Example example = new Example(SysRoleResource.class);
		example.createCriteria().andEqualTo("roleId", roleId);
		sysRoleResourceDao.deleteByExample(example);
	}
}
