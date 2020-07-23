package com.test.project.web.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.test.project.model.SysModule;

public class ModuleReq {
	private Integer id;

	@NotEmpty
	private String name;// 菜单名

	@NotNull
	private Integer type;// 类型

	private String component;// 资源文件

	private String path;// url路径

	@Min(1)
	private Integer orderNum;

	private String iconCls;

	@Length(max = 100)
	private String remark;

	@NotNull
	private Integer pid;

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

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public SysModule convert() {
		SysModule module = new SysModule();
		module.setId(id);
		module.setName(name);
		module.setOrderNum(orderNum);
		module.setRemark(remark);
		module.setCode(component);
		module.setIconCls(iconCls);
		module.setType(type);
		module.setUrl(path);
		module.setParentId(pid);

		return module;
	}
}
