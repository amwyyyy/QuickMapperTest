package com.test.project.db.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Select;

import com.test.project.db.po.SysModulePO;
import com.wen.mapper.common.IBaseMapper;

public interface SysModulePOMapper extends IBaseMapper<SysModulePO, Integer> {

	/**
	 * 用户所有的功能
	 * 
	 * @param username
	 * @return
	 */
	@Select(value = "select m.url from sys_user u left join sys_user_role ur on ur.user_id=u.id "
			+ "left join sys_role r on r.id = ur.role_id left join sys_role_module rm on rm.role_id=r.id "
			+ "left join sys_module m on rm.module_id=m.id where u.username=#{username} and type>2")
	Set<String> selectFunByUsername(String username);

	/**
	 * 所有功能
	 * 
	 * @return
	 */
	@Select(value = "select m.url from sys_module m where type>2")
	Set<String> selectAllFuns();

	/**
	 * 用户拥有的菜单
	 * 
	 * @param userId
	 * @return
	 */
	List<SysModulePO> selectUserMenus(int userId);

	/**
	 * 查询用户所有模块
	 * 
	 * @param roleId
	 * @return
	 */
	List<SysModulePO> selectModules(int roleId);
}