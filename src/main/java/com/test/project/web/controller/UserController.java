package com.test.project.web.controller;

import com.github.pagehelper.Page;
import com.test.project.common.QuickBaseController;
import com.test.project.model.SysUser;
import com.test.project.service.SysUserService;
import com.test.project.web.vo.UserReq;
import com.wen.commons.spring.validation.Id;
import com.wen.commons.web.PageParams;
import com.wen.commons.web.ResultObject;
import org.apache.commons.lang.BooleanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/user")
public class UserController extends QuickBaseController
{
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 用户登录
	 *
	 * @param username
	 * @param password
	 * @param rememberMe
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResultObject login(@NotEmpty String username, @NotEmpty String password,
			@RequestParam(required = false) Boolean rememberMe) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(BooleanUtils.isTrue(rememberMe));
		subject.login(token);

		saveLoginUser(username);

		return buildSuccessResp();
	}

	/**
	 * 用户退出登录
	 *
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	@RequiresUser
	public ResultObject logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return buildSuccessResp();
	}

	/**
	 * 获取用户列表
	 *
	 * @param username
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	@RequiresRoles("admin")
	public ResultObject list(String username, @Valid PageParams page) {
		Page<SysUser> list = sysUserService.selectUserList(username, page);
		return toPageResp(list);
	}

	/**
	 * 添加用户
	 *
	 * @param userReq
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@RequiresRoles("admin")
	public ResultObject add(@Valid UserReq userReq) {
		SysUser user = userReq.convert();
		int userId = sysUserService.insert(user);

		return buildSuccessResp();
	}

	/**
	 * 更新用户
	 *
	 * @param userReq
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	@RequiresRoles("admin")
	public ResultObject update(@Valid UserReq userReq) {
		SysUser user = userReq.convert();
		user.setPassword(null);// 不更新密码
		sysUserService.updateById(user);

		return buildSuccessResp();
	}

	/**
	 * 删除用户
	 *
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	@RequiresRoles("admin")
	public ResultObject del(@Id Integer userId) {
		sysUserService.deleteById(userId);
		return buildSuccessResp();
	}
}