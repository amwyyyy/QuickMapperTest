package com.test.project.web.vo;

import com.test.project.model.SysRole;
import org.hibernate.validator.constraints.NotEmpty;

public class RoleReq {
	private Integer id;

	@NotEmpty
	private String name;

	private String remark;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public SysRole convert() {
		SysRole role = new SysRole();
		role.setName(name);
		role.setRemark(remark);
		role.setId(id);
		return role;
	}
}
