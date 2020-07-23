package com.test.project.web.vo;

public class AssignRoleResp {
	private Integer id;
	private String name;// 角色名
	private Boolean checked;// 是否已拥有

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

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}
