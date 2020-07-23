package com.test.project.common;

import com.wen.commons.web.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.test.project.model.SysUser;
import com.test.project.service.SysUserService;
import com.wen.commons.exception.ErrorCode;
import com.wen.commons.exception.ErrorCodeConst;
import com.wen.commons.utils.StringUtils;
import com.wen.commons.web.ResultObject;

/**
 * 业务controller基类
 * @author huangwg
 * @since 2016-10-10
 */
public abstract class QuickBaseController extends BaseController {
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 获取登录的用户
	 * 
	 * @return
	 */
	protected SysUser getLoginUser() {
		Subject subject = SecurityUtils.getSubject();

		SysUser user = (SysUser) subject.getSession().getAttribute("user");
		if (user != null) {
			return user;
		} else if (subject.isRemembered()) {
			// 如果开启记住我，则尝试从cookie中取用户
			String username = (String) subject.getPrincipal();

			if (StringUtils.isNotEmpty(username)) {
				user = saveLoginUser(username);
			}
		}
		
		if (user != null) {
			return user;
		}
		throw new UnauthenticatedException();
	}

	/**
	 * 获取登录用户id
	 * 
	 * @return
	 */
	protected int getLoginUserId() {
		return getLoginUser().getId();
	}

	/**
	 * 保存登录用户
	 * 
	 * @param username
	 */
	protected SysUser saveLoginUser(String username) {
		SysUser user = sysUserService.selectByUsername(username);

		if (user != null) {
			Subject subject = SecurityUtils.getSubject();
			subject.getSession(true).setAttribute("user", user);
		}

		return user;
	}
}
