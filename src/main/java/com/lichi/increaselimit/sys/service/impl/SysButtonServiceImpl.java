package com.lichi.increaselimit.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.lichi.increaselimit.common.enums.ResultEnum;
import com.lichi.increaselimit.common.exception.BusinessException;
import com.lichi.increaselimit.sys.dao.SysButtonDao;
import com.lichi.increaselimit.sys.dao.SysMenuDao;
import com.lichi.increaselimit.sys.entity.SysButton;
import com.lichi.increaselimit.sys.entity.SysMenu;
import com.lichi.increaselimit.sys.service.SysButtonService;

import tk.mybatis.mapper.entity.Example;

@Service
public class SysButtonServiceImpl implements SysButtonService {

	@Autowired
	private SysButtonDao buttonDao;
	@Autowired
	private SysMenuDao sysMenuDao;

	@Override
	public SysButton selectOne(Integer id) {
		return buttonDao.selectByPrimaryKey(id);
	}

	@Override
	public void insertOne(SysButton button) {
		button.setUpdateTime(new Date());
		button.setCreateTime(new Date());
		SysMenu menu = sysMenuDao.selectByPrimaryKey(button.getMenuId());
		if(menu == null) {
			throw new BusinessException(ResultEnum.MENU_NOT_EXIST);
		}
		buttonDao.insertSelective(button);
	}

	@Override
	public void deleteOne(Integer id) {
		buttonDao.deleteByPrimaryKey(id);
	}

	@Override
	public void update(SysButton Button) {
		Button.setUpdateTime(new Date());
		buttonDao.updateByPrimaryKeySelective(Button);

	}

	@Override
	public List<SysButton> selectByMenuId(Integer menuId) {
		PageHelper.orderBy("create_time desc");
		Example example = new Example(SysButton.class);
		example.createCriteria().andEqualTo("menuId", menuId);
		List<SysButton> list = buttonDao.selectByExample(example);
		return list;
	}

}
