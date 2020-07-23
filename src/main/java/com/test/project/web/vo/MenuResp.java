package com.test.project.web.vo;

import com.test.project.model.SysModule;

/**
 * Created by amwyyyy on 2016/12/11.
 */
public class MenuResp {
	private Integer id; // 菜单id
	private Integer type;// 菜单类型，1主菜单，2子菜单，3权限
	private String path;// 路径
	private String component;// 页面
	private String name;// 菜单名
	private String iconCls;// 图标
	private Integer pid;// 父节点Id
	private Integer orderNum;// 排序
	private String remark;// 备注

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public static MenuResp convertTo(SysModule menu) {
		MenuResp resp = new MenuResp();
		resp.setId(menu.getId());
		resp.setPid(menu.getParentId());
		resp.setType(menu.getType());
		resp.setComponent(menu.getCode());
		resp.setIconCls(menu.getIconCls());
		resp.setName(menu.getName());
		resp.setPath(menu.getUrl());
		resp.setOrderNum(menu.getOrderNum());
		resp.setRemark(menu.getRemark());
		return resp;
	}
}
