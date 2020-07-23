package com.test.project.service.impl;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.project.db.mapper.SysModulePOMapper;
import com.test.project.db.mapper.SysRoleModulePOMapper;
import com.test.project.db.po.SysModulePO;
import com.test.project.db.po.SysModulePOExample;
import com.test.project.db.po.SysRoleModulePO;
import com.test.project.db.po.SysRoleModulePOExample;
import com.test.project.enums.ModuleType;
import com.test.project.model.SysModule;
import com.test.project.service.SysModuleService;
import com.wen.commons.BaseService;
import com.wen.commons.exception.BusinessException;
import com.wen.commons.exception.ErrorCode;
import com.wen.commons.mybatis.orderbyhelper.OrderByHelper;

@Service
public class SysModuleServiceImpl extends BaseService implements SysModuleService {
	@Autowired
	private SysModulePOMapper sysModuleMapper;
	@Autowired
	private SysRoleModulePOMapper sysRoleModuleMapper;

	@Override
	public Set<String> selectFunction(String username) {
		Set<String> funs = sysModuleMapper.selectFunByUsername(username);

		Set<String> funSet = new HashSet<>();
		funs.stream().filter(StringUtils::isNotEmpty).forEach(fun ->
			Collections.addAll(funSet, fun.split(";"))
		);

		return funSet;
	}

	@Override
	public Set<String> selectFunctions() {
		Set<String> funs = sysModuleMapper.selectAllFuns();

		Set<String> funSet = new HashSet<>();
		funs.stream().filter(StringUtils::isNotEmpty).forEach(perm ->
			Collections.addAll(funSet, perm.split(";"))
		);

		return funSet;
	}

	@Override
	public List<SysModule> selectMenus(Integer type, Integer parentId) {
		SysModulePOExample ex = new SysModulePOExample();
		ex.createCriteria().andTypeEqualTo(type).andParentIdEqualTo(parentId);

		OrderByHelper.orderBy("order_num");
		List<SysModulePO> poList = sysModuleMapper.selectByExample(ex);

		return listConvert(poList, SysModule::convertTo);
	}

	private SysModulePO selectById(Integer moduleId) {
		SysModulePO modulePO = sysModuleMapper.selectByPrimaryKey(moduleId);
		if (modulePO == null) {
			throw new BusinessException(new ErrorCode(100, "模块不存在"));
		}
		return modulePO;
	}

	@Override
	public void deleteById(Integer moduleId) {
		selectById(moduleId);

		sysModuleMapper.deleteByPrimaryKey(moduleId);

		logger.info("delete module success moduleId={}", moduleId);
	}

	@Override
	public void updateById(SysModule module) {
		selectById(module.getId());

		SysModulePO po = module.convert();
		po.setUpdateTime(new Date());
		sysModuleMapper.updateByPrimaryKeySelective(po);

		logger.info("update module success moduleId={}", module.getId());
	}

	@Override
	public int insert(SysModule module) {
		SysModulePO po = module.convert();
		po.setCreateTime(new Date());
		po.setUpdateTime(new Date());
		sysModuleMapper.insert(po);

		return po.getId();
	}

	@Override
	public List<SysModule> selectModules(Integer roleId) {
		List<SysModulePO> poList;
		if (roleId == null) {
			OrderByHelper.orderBy("order_num");
			poList = sysModuleMapper.selectByExample(new SysModulePOExample());
		} else {
			poList = sysModuleMapper.selectModules(roleId);
		}

		return listConvert(poList, SysModule::convertTo);
	}

	@Override
	public List<SysModule> selectUserMenus(Integer userId) {
		List<SysModulePO> poList;
		if (userId == 1) {
			SysModulePOExample ex = new SysModulePOExample();
			ex.createCriteria().andTypeIn(Arrays.asList(ModuleType.Menu.getIndex(), ModuleType.Submenu.getIndex()));

			OrderByHelper.orderBy("order_num");
			poList = sysModuleMapper.selectByExample(ex);
		} else {
			poList = sysModuleMapper.selectUserMenus(userId);
		}

		return listConvert(poList, SysModule::convertTo);
	}

	@Override
	public void assignModule(Integer roleId, List<Integer> moduleIds) {
		// 删除旧权限
		deleteModule(roleId);

		// 插入权限
		insertModule(roleId, moduleIds);

		logger.info("role assign module success roleId={}|moduleIds={}", roleId, StringUtils.join(moduleIds, ","));
	}

	/**
	 * 插入权限
	 * 
	 * @param roleId
	 * @param moduleIds
	 */
	private void insertModule(Integer roleId, List<Integer> moduleIds) {
		moduleIds.forEach(moduleId -> {
			SysRoleModulePO po = new SysRoleModulePO();
			po.setCreateTime(new Date());
			po.setModuleId(moduleId);
			po.setRoleId(roleId);
			sysRoleModuleMapper.insert(po);
		});
	}

	/**
	 * 删除角色权限
	 * 
	 * @param roleId
	 */
	private void deleteModule(Integer roleId) {
		SysRoleModulePOExample ex = new SysRoleModulePOExample();
		ex.createCriteria().andRoleIdEqualTo(roleId);
		sysRoleModuleMapper.deleteByExample(ex);
	}

	@Override
	public void loadParentModuleId(Integer[] moduleIds, Set<Integer> moduleIdd) {
		sysModuleMapper.selectByPrimaryKeys(moduleIds).forEach(module -> {
			moduleIdd.add(module.getId());
			if (module.getParentId() != 0) {
				loadParentModuleId(new Integer[] { module.getParentId() }, moduleIdd);
			}
		});
	}
}
