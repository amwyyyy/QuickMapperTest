package com.test.project.web.controller;

import com.test.project.config.DBRealm;
import com.test.project.common.QuickBaseController;
import com.test.project.model.SysModule;
import com.test.project.service.SysModuleService;
import com.test.project.web.vo.MenuResp;
import com.test.project.web.vo.ModuleReq;
import com.wen.commons.spring.validation.Id;
import com.wen.commons.web.ResultObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 权限管理 Created by amwyyyy on 2016/12/11.
 */
@Controller
@RequestMapping("/module")
public class ModuleController extends QuickBaseController
{
	@Autowired
	private SysModuleService moduleService;
	@Autowired
	private DBRealm dbRealm;

	/**
	 * 获取用户菜单
	 *
	 * @return
	 */
	@RequestMapping(value = "/menus", method = RequestMethod.GET)
	@ResponseBody
	public ResultObject getMenus() {
		int userId = getLoginUserId();
		List<SysModule> menus = moduleService.selectUserMenus(userId);

		List<MenuResp> respList = menus.stream().map(MenuResp::convertTo).collect(Collectors.toList());

		return buildSuccessResp(respList);
	}

	/**
	 * 查询菜单
	 *
	 * @param type
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "/load/menu", method = RequestMethod.GET)
	@ResponseBody
	@RequiresRoles("admin")
	public ResultObject loadMenus(@Id Integer type, @NotNull Integer parentId) {
		List<SysModule> menus = moduleService.selectMenus(type, parentId);

		List<MenuResp> respList = menus.stream().map(MenuResp::convertTo).collect(Collectors.toList());

		return buildSuccessResp(respList);
	}

	/**
	 * 删除菜单
	 *
	 * @param moduleId
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	@RequiresRoles("admin")
	public ResultObject delMenu(@Id Integer moduleId) {
		moduleService.deleteById(moduleId);
		return buildSuccessResp();
	}

	/**
	 * 更新菜单
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	@RequiresRoles("admin")
	public ResultObject updateMenu(@Valid ModuleReq req) {
		SysModule module = req.convert();

		moduleService.updateById(module);
		return buildSuccessResp();
	}

	/**
	 * 添加菜单
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@RequiresRoles("admin")
	public ResultObject addMenu(@Valid ModuleReq req) {
		SysModule module = req.convert();
		int moduleId = moduleService.insert(module);

		logger.info("add menu success moduleId={}", moduleId);
		return buildSuccessResp();
	}

	/**
	 * 获取用户所有模块
	 *
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/load/all", method = RequestMethod.GET)
	@ResponseBody
	@RequiresRoles("admin")
	public ResultObject getUserModule(@Id Integer roleId) {
		// 所有的模块
		List<SysModule> allModules = moduleService.selectModules(null);
		List<MenuResp> respList = allModules.stream().map(MenuResp::convertTo).collect(Collectors.toList());

		// 拥有的模块
		List<SysModule> hasModules = moduleService.selectModules(roleId);
		// 生成树
		hasModules = generateChildren(0, hasModules);
		// 获取叶子节点Id,因为树形插件的原因
		List<Integer> childrenIds = new ArrayList<>();
		getChildrenIds(childrenIds, hasModules);

		Map<String, Object> resp = new HashMap<>();
		resp.put("modules", respList);
		resp.put("hasModules", childrenIds);

		return buildSuccessResp(resp);
	}

	/**
	 * 获取叶子节点Id
	 * 
	 * @param hasModules
	 * @return
	 */
	private void getChildrenIds(List<Integer> childrenIds, List<SysModule> hasModules) {
		hasModules.forEach(module -> {
			if (CollectionUtils.isEmpty(module.getChildren())) {
				childrenIds.add(module.getId());
			} else {
				getChildrenIds(childrenIds, module.getChildren());
			}
		});
	}

	/**
	 * 生成树形结构
	 * 
	 * @param pid
	 * @param modules
	 * @return
	 */
	private List<SysModule> generateChildren(int pid, List<SysModule> modules) {
		List<SysModule> moduleList = new ArrayList<>();
		modules.forEach(module -> {
			if (module.getParentId().equals(pid)) {
				module.setChildren(generateChildren(module.getId(), modules));
				moduleList.add(module);
			}
		});
		return moduleList;
	}

	/**
	 * 给角色授权
	 * 
	 * @param roleId 角色id
	 * @param moduleIds 模块id数组
	 * @return result
	 */
	@RequestMapping(value = "/assign", method = RequestMethod.POST)
	@ResponseBody
	@RequiresRoles("admin")
	public ResultObject assignModule(@Id Integer roleId, Integer[] moduleIds) {
		// 由于传过来的只有子节点的id，需要把父节点id加进去
		Set<Integer> moduleIdd = new HashSet<>();
		moduleService.loadParentModuleId(moduleIds, moduleIdd);

		moduleService.assignModule(roleId, new ArrayList<>(moduleIdd));

		// 清空shiro缓存
		dbRealm.clearCache();

		return buildSuccessResp();
	}
}
