package com.test.project.web.controller;

import com.github.pagehelper.Page;
import com.test.project.config.DBRealm;
import com.test.project.common.QuickBaseController;
import com.test.project.model.SysRole;
import com.test.project.service.SysRoleService;
import com.test.project.web.vo.AssignRoleResp;
import com.test.project.web.vo.RoleReq;
import com.wen.commons.spring.validation.Id;
import com.wen.commons.web.PageParams;
import com.wen.commons.web.ResultObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色管理
 */
@Controller
@RequestMapping("/role")
public class RoleController extends QuickBaseController
{
	@Autowired
	private SysRoleService roleService;
	@Autowired
	private DBRealm dbRealm;

	/**
	 * 角色列表
	 *
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	@RequiresRoles("admin")
	public ResultObject list(String name, @Valid PageParams page) {
		Page<SysRole> list = roleService.selectRoleList(name, page);
		return toPageResp(list);
	}

	/**
	 * 添加角色
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@RequiresRoles("admin")
	public ResultObject add(@Valid RoleReq req) {
		SysRole role = req.convert();
		int roleId = roleService.insert(role);

		return buildSuccessResp();
	}

	/**
	 * 更新角色
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	@RequiresRoles("admin")
	public ResultObject update(@Valid RoleReq req) {
		SysRole role = req.convert();
		roleService.updateById(role);

		return buildSuccessResp();
	}

	/**
	 * 删除角色
	 *
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	@RequiresRoles("admin")
	public ResultObject del(@Id Integer roleId) {
		roleService.deleteById(roleId);
		return buildSuccessResp();
	}

	/**
	 * 可分配角色列表
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/assign/list", method = RequestMethod.GET)
	@ResponseBody
	@RequiresRoles("admin")
	public ResultObject roleList(@Id Integer userId) {
		// 拥有的角色
		List<SysRole> hasRoles = roleService.selectRolesByUserId(userId);
		// 所有的角色
		List<SysRole> roles = roleService.selectRoleList();

		List<AssignRoleResp> resp = roles.stream().map(role -> {
			AssignRoleResp r = new AssignRoleResp();
			r.setId(role.getId());
			r.setName(role.getName());
			r.setChecked(hasRoles.contains(role));
			return r;
		}).collect(Collectors.toList());

		return buildSuccessResp(resp);
	}

	/**
	 * 角色分配
	 * 
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value = "/assign", method = RequestMethod.POST)
	@ResponseBody
	@RequiresRoles("admin")
	public ResultObject addUserRole(@Id Integer userId, Integer[] roleIds) {
		roleService.assignRole(userId, Arrays.asList(roleIds));

		// 清空shiro缓存
		dbRealm.clearCache();

		return buildSuccessResp();
	}
}
