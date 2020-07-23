package com.test.project.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.test.project.db.mapper.SysRolePOMapper;
import com.test.project.db.mapper.SysUserRolePOMapper;
import com.test.project.db.po.SysRolePO;
import com.test.project.db.po.SysRolePOExample;
import com.test.project.db.po.SysUserRolePO;
import com.test.project.db.po.SysUserRolePOExample;
import com.test.project.model.SysRole;
import com.test.project.service.SysRoleService;
import com.wen.commons.BaseService;
import com.wen.commons.exception.BusinessException;
import com.wen.commons.exception.ErrorCode;
import com.wen.commons.web.PageParams;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class SysRoleServiceImpl extends BaseService implements SysRoleService {
	@Autowired
	private SysRolePOMapper sysRoleMapper;
	@Autowired
	private SysUserRolePOMapper sysUserRoleMapper;

	@Override
	public Set<String> selectByUsername(String username) {
		return sysRoleMapper.selectByUsername(username);
	}

	@Override
	public Page<SysRole> selectRoleList(String name, PageParams page) {
		SysRolePOExample ex = new SysRolePOExample();
		if (StringUtils.isNotEmpty(name)) {
			ex.createCriteria().andNameLike("%" + name + "%");
		}

		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		Page<SysRolePO> poList = (Page<SysRolePO>) sysRoleMapper.selectByExample(ex);
		return pageConvert(poList, SysRole::convertTo);
	}

	@Override
	public int insert(SysRole role) {
		SysRolePO po = role.convert();
		po.setCreateTime(new Date());
		po.setUpdateTime(new Date());

		sysRoleMapper.insert(po);

		logger.info("添加角色成功,{}", po.getId());

		return po.getId();
	}

	@Override
	@CacheEvict(cacheNames="queryCache", allEntries = true)
	public void updateById(SysRole role) {
		selectById(role.getId());

		SysRolePO po = role.convert();
		po.setUpdateTime(new Date());
		sysRoleMapper.updateByPrimaryKeySelective(po);

		logger.info("更新角色成功,{}", role.getName());
	}

	@Override
	@CacheEvict(cacheNames="queryCache", allEntries = true)
	public void deleteById(Integer roleId) {
		selectById(roleId);

		sysRoleMapper.deleteByPrimaryKey(roleId);

		logger.info("delete role success roleId = {}", roleId);
	}

	@Override
	public SysRole selectById(Integer roleId) {
		SysRolePO rolePO = sysRoleMapper.selectByPrimaryKey(roleId);
		if (rolePO == null) {
			throw new BusinessException(new ErrorCode(100, "角色不存在"));
		}
		return SysRole.convertTo(rolePO);
	}

	@Override
	@Cacheable(cacheNames = "queryCache", key = "'cache_user_role_'.concat(#userId)")
	public List<SysRole> selectRolesByUserId(Integer userId) {
		List<SysRolePO> roleList = sysRoleMapper.selectRolesByUserId(userId);
		return listConvert(roleList, SysRole::convertTo);
	}

	@Override
	public List<SysRole> selectRoleList() {
		return listConvert(sysRoleMapper.selectByExample(new SysRolePOExample()), SysRole::convertTo);
	}

	@Override
	@CacheEvict(cacheNames="queryCache", key="'cache_user_role_'.concat(#userId)")
	public void assignRole(Integer userId, List<Integer> roleIds) {
		// 先删除老角色
		deleteRole(userId);

		// 插入新角色
		insertRole(userId, roleIds);

		logger.info("user assign role success userId={}|roleIds={}", userId, StringUtils.join(roleIds, ","));
	}

	/**
	 * 用户添加新角色
	 * 
	 * @param userId
	 * @param roleIds
	 */
	private void insertRole(Integer userId, List<Integer> roleIds) {
		roleIds.forEach(roleId -> {
			SysUserRolePO rolePO = new SysUserRolePO();
			rolePO.setCreateTime(new Date());
			rolePO.setRoleId(roleId);
			rolePO.setUserId(userId);
			sysUserRoleMapper.insert(rolePO);
		});
	}

	/**
	 * 删除用户角色
	 * 
	 * @param userId
	 */
	private void deleteRole(Integer userId) {
		SysUserRolePOExample example = new SysUserRolePOExample();
		example.createCriteria().andUserIdEqualTo(userId);
		sysUserRoleMapper.deleteByExample(example);
	}
}