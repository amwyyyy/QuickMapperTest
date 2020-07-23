package com.test.project.enums;

/**
 * 权限类型
 * 
 * @author Administrator
 */
public enum ModuleType {
	/** 根菜单 */
	Root(0),

	/** 一级菜单 */
	Menu(1),

	/** 二级菜单 */
	Submenu(2),

	/** 功能 */
	Function(3);

	private int index;

	ModuleType(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
