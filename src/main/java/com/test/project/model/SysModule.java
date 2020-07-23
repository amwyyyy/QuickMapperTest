package com.test.project.model;

import java.io.Serializable;
import java.util.List;

import com.test.project.db.po.SysModulePO;

public class SysModule implements Serializable {
	private static final long serialVersionUID = 85668107063497973L;

	private Integer id;

	private String name;// 权限名

	private Integer type;// 权限类型

	private String code;// 权限代码

	private String url;// 权限

	private Integer parentId;// 父级id

	private String iconCls;// 图标

	private Integer orderNum;// 排序

	private String remark;// 备注

	private List<SysModule> children;

	public List<SysModule> getChildren() {
		return children;
	}

	public void setChildren(List<SysModule> children) {
		this.children = children;
	}

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public static SysModule convertTo(SysModulePO value) {
		SysModule module = new SysModule();
		module.setCode(value.getCode());
		module.setIconCls(value.getIconCls());
		module.setId(value.getId());
		module.setName(value.getName());
		module.setParentId(value.getParentId());
		module.setType(value.getType());
		module.setUrl(value.getUrl());
		module.setOrderNum(value.getOrderNum());
		module.setRemark(value.getRemark());
		return module;
	}

	public SysModulePO convert() {
		SysModulePO po = new SysModulePO();
		po.setId(id);
		po.setCode(code);
		po.setIconCls(iconCls);
		po.setName(name);
		po.setOrderNum(orderNum);
		po.setParentId(parentId);
		po.setRemark(remark);
		po.setType(type);
		po.setUrl(url);

		return po;
	}
}
