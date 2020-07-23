package com.test.project.service;

import com.github.pagehelper.Page;
import com.test.project.model.SysUser;
import com.wen.commons.web.PageParams;

public interface SysUserService {
	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 * @return
	 */
	SysUser selectByUsername(String username);

	/**
	 * 查询所有用户,并分页
	 * 
	 * @return
	 */
	Page<SysUser> selectUserList(String username, PageParams page);

	/**
	 * 新增用户
	 * 
	 * @param user
	 */
	int insert(SysUser user);

	/**
	 * 更新用户
	 * 
	 * @param user
	 */
	void updateById(SysUser user);

	/**
	 * 删除用户
	 * 
	 * @param userId
	 */
	void deleteById(Integer userId);

	/**
	 * 根据id查询用户
	 * 
	 * @param userId
	 * @return
	 */
	SysUser selectById(Integer userId);
}