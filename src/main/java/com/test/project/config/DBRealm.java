package com.test.project.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.project.model.SysUser;
import com.test.project.service.SysModuleService;
import com.test.project.service.SysRoleService;
import com.test.project.service.SysUserService;
import com.wen.commons.utils.MD5Utils;

/**
 * 身份认证器
 * 
 * @author denis.huang
 * @since 2017年2月15日
 */
@Component("dbRealm")
public class DBRealm extends AuthorizingRealm {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysModuleService sysModuleService;

	@Override
	public String getName() {
		return "dbRealm";
	}

	@Autowired
	public DBRealm(CacheManager cacheManager)
	{
		this.setCacheManager(cacheManager);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String pwd = new String((char[]) token.getCredentials());

		// 验证用户名密码
		SysUser po = sysUserService.selectByUsername(username);
		if (po == null) {
			throw new UnknownAccountException();
		} else if (!po.getPassword().equals(MD5Utils.encode2Base64(pwd.getBytes()))) {
			throw new IncorrectCredentialsException();
		}

		return new SimpleAuthenticationInfo(username, pwd, getName());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();

		//设置角色和权限
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(sysRoleService.selectByUsername(username));
		authorizationInfo.setStringPermissions(sysModuleService.selectFunction(username));
		return authorizationInfo;
	}

	/**
	 * 清理认证信息缓存
	 */
	public void clearCache() {
		getAuthorizationCache().clear();
	}
}
