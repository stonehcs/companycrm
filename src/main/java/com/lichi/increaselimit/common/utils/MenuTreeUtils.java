package com.lichi.increaselimit.common.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lichi.increaselimit.sys.entity.SysMenu;

/**
 * 获取菜单树
 * @author majie
 *
 */
public class MenuTreeUtils {
	
	public static JSONArray treeMenuList(List<SysMenu> menuList, int parentId) {
		JSONArray childMenu = new JSONArray();
		for (SysMenu object : menuList) {
			JSONObject jsonMenu = (JSONObject) JSON.toJSON(object);
			int menuId = (int) jsonMenu.get("id");
			int pid = (int) jsonMenu.get("pid");
			if (parentId == pid) {
				JSONArray childs = treeMenuList(menuList, menuId);
				jsonMenu.put("childs", childs);
				childMenu.add(jsonMenu);
			}
		}
		return childMenu;
	}

}
