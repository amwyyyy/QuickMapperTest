package com.test.project.model;

import java.io.Serializable;
import java.util.Date;

import com.test.project.db.po.SysRolePO;

public class SysRole implements Serializable {
	private static final long serialVersionUID = -4744026461461170209L;

	private Integer id;
	private String name;// 角色名
	private String remark;// 备注
	private Date createTime;// 创建时间

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static SysRole convertTo(SysRolePO input) {
		if (input == null) {
			return null;
		}

		SysRole role = new SysRole();
		role.setCreateTime(input.getCreateTime());
		role.setId(input.getId());
		role.setName(input.getName());
		role.setRemark(input.getRemark());
		return role;
	}

	public SysRolePO convert() {
		SysRolePO po = new SysRolePO();
		po.setName(name);
		po.setRemark(remark);
		po.setId(id);
		return po;
	}

	@Override
	public boolean equals(Object obj) {
		SysRole role = (SysRole) obj;
		if (role == null) {
			return false;
		}

		return eq(id, role.getId()) && eq(name, role.getName()) && eq(remark, role.getRemark())
				&& eq(createTime, role.getCreateTime());
	}

	private static <T> boolean eq(T o1, T o2) {
		return o1 == null ? o2 == null : o1.equals(o2);
	}
}
