package com.test.project.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.test.project.db.po.SysUserPO;

public class SysUser implements Serializable {
	private static final long serialVersionUID = 5268979245025574953L;

	private Integer id;

	private String username;// 用户名

	private String password;// 密码

	private String mobile;// 手机号

	private Date createTime;// 创建时间

	private Date updateTime;// 更新时间

	private Set<String> roles;// 拥有的角色

	private Set<String> permissions;// 拥有的权限

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public static SysUser convertTo(SysUserPO po) {
		if (po == null) {
			return null;
		}
		SysUser user = new SysUser();
		user.setCreateTime(po.getCreateTime());
		user.setId(po.getId());
		user.setMobile(po.getMobile());
		user.setPassword(po.getPassword());
		user.setUpdateTime(po.getUpdateTime());
		user.setUsername(po.getUsername());
		return user;
	}

	public SysUserPO convert() {
		SysUserPO po = new SysUserPO();
		po.setCreateTime(this.createTime);
		po.setId(this.id);
		po.setMobile(this.mobile);
		po.setPassword(this.password);
		po.setUpdateTime(this.updateTime);
		po.setUsername(this.getUsername());

		return po;
	}
}
