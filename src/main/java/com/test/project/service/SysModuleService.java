package com.test.project.service;

import java.util.List;
import java.util.Set;

import com.test.project.model.SysModule;
import com.wen.mapper.dataSource.Slave;

public interface SysModuleService {

	/**
	 * 根据用户名查找用户所有功能
	 * 
	 * @param username
	 * @return
	 */
	@Slave
	Set<String> selectFunction(String username);

	/**
	 * 查询所有功能
	 * 
	 * @return
	 */
	@Slave
	Set<String> selectFunctions();

	/**
	 * 查询菜单
	 * 
	 * @param type 菜单类型
	 * @param parentId 父菜单id
	 * @return
	 */
	@Slave
	List<SysModule> selectMenus(Integer type, Integer parentId);

	/**
	 * 查询用户菜单
	 * 
	 * @param userId
	 * @return
	 */
	List<SysModule> selectUserMenus(Integer userId);

	/**
	 * 查询模块
	 * 
	 * @param roleId
	 * @return
	 */
	List<SysModule> selectModules(Integer roleId);

	/**
	 * 根据id删除模块
	 * 
	 * @param moduleId
	 */
	void deleteById(Integer moduleId);

	/**
	 * 更新模块
	 * 
	 * @param module
	 */
	void updateById(SysModule module);

	/**
	 * 新增模块
	 * 
	 * @param module
	 * @return
	 */
	int insert(SysModule module);

	/**
	 * 给角色分配权限
	 * 
	 * @param roleId
	 * @param moduleIds
	 */
	void assignModule(Integer roleId, List<Integer> moduleIds);

	/**
	 * 将模块父节点id加进去
	 * 
	 * @param moduleIds
	 * @return
	 */
	void loadParentModuleId(Integer[] moduleIds, Set<Integer> moduleIdd);
}