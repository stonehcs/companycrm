package com.lichi.increaselimit.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.sys.dao.SysMenuDao;
import com.lichi.increaselimit.sys.entity.SysButton;
import com.lichi.increaselimit.sys.entity.SysMenu;
import com.lichi.increaselimit.sys.service.SysButtonService;
import com.lichi.increaselimit.sys.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService{
	@Autowired
	private SysMenuDao menuDao;
	@Autowired
	private SysButtonService buttonService;

	@Override
	public SysMenu selectOne(Integer id) {
		return menuDao.selectByPrimaryKey(id);
	}

	@Override
	public void insertOne(SysMenu menu) {
		SysMenu menuresult = menuDao.selectByName(menu.getMenuName());
		if(menuresult != null) {
			throw new BusinessException(ResultEnum.MENU_EXIST);
		}
		menu.setUpdateTime(new Date());
		menu.setCreateTime(new Date());
		menuDao.insertSelective(menu);
	}

	@Override
	public void deleteOne(Integer id) {
		List<SysMenu> list = menuDao.selectChild(id);
		if(list != null && list.size() > 0) {
			throw new BusinessException(ResultEnum.HAVE_CHILD_MENU);
		}
		List<SysButton> buttonList = buttonService.selectByMenuId(id);
		if(buttonList != null && buttonList.size() > 0) {
			throw new BusinessException(ResultEnum.HAVE_BUTTON);
		}
		menuDao.deleteByPrimaryKey(id);
	}

	@Override
	public void update(SysMenu menu) {
		menu.setUpdateTime(new Date());
		menuDao.updateByPrimaryKeySelective(menu);
		
	}

	@Override
	public List<SysMenu> selectList() {
		PageHelper.orderBy("create_time desc");
		List<SysMenu> list = menuDao.selectAll();
		return list;
	}

}
