package com.test.project.service;

import java.util.List;
import java.util.Set;

import com.github.pagehelper.Page;
import com.test.project.model.SysRole;
import com.wen.commons.web.PageParams;
import org.springframework.cache.annotation.Cacheable;

public interface SysRoleService {

	/**
	 * 根据用户名查找用户所有角色
	 * 
	 * @param username
	 * @return
	 */
	Set<String> selectByUsername(String username);

	/**
	 * 查询所有角色,分页
	 * 
	 * @param name
	 * @param page
	 * @return
	 */
	Page<SysRole> selectRoleList(String name, PageParams page);

	/**
	 * 插入新角色
	 * 
	 * @param role
	 * @return
	 */
	int insert(SysRole role);

	/**
	 * 更新角色
	 * 
	 * @param role
	 */
	void updateById(SysRole role);

	/**
	 * 根据id删除角色
	 * 
	 * @param roleId
	 */
	void deleteById(Integer roleId);

	/**
	 * 根据id加载角色信息
	 * 
	 * @param roleId
	 * @return
	 */
	SysRole selectById(Integer roleId);

	/**
	 * 根据用户id查询所有角色
	 * 
	 * @param userId
	 * @return
	 */
	List<SysRole> selectRolesByUserId(Integer userId);

	/**
	 * 查询所有角色，不分页
	 * 
	 * @return
	 */
	List<SysRole> selectRoleList();

	/**
	 * 给用户分配角色
	 * 
	 * @param userId
	 * @param roleIds
	 */
	void assignRole(Integer userId, List<Integer> roleIds);
}