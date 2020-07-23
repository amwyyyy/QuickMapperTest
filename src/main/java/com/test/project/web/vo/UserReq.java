package com.test.project.web.vo;

import javax.validation.constraints.Size;

import com.test.project.model.SysUser;
import com.wen.commons.utils.MD5Utils;
import org.hibernate.validator.constraints.NotEmpty;

public class UserReq {
	private Integer id;

	@NotEmpty
	private String username;

	@NotEmpty
	@Size(min = 1, max = 11)
	private String mobile;

	private String password;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SysUser convert() {
		SysUser user = new SysUser();
		user.setId(id);
		user.setMobile(mobile);
		user.setUsername(username);
		user.setPassword(MD5Utils.encode2Base64(password.getBytes()));
		return user;
	}
}
