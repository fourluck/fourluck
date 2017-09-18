package com.zdd.plugin.security.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import com.zdd.plugin.security.FourluckSecurity;
import com.zdd.plugin.security.SecurityConstant;
import com.zdd.plugin.security.password.Md5CredentialsMatcher;

/**
 * 基于 fouluck 的自定义 Realm (需要实现 FourluckSecurity 接口)
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月15日 上午10:18:16
 */
public class FourluckCustomRealm extends AuthorizingRealm {

	private final FourluckSecurity fourluckSecurity;
	
	
	public FourluckCustomRealm(FourluckSecurity fourluckSecurity) {
		this.fourluckSecurity = fourluckSecurity;
		super.setName(SecurityConstant.REALMS_CUSTOM);
		super.setCredentialsMatcher(new Md5CredentialsMatcher());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if(principals == null){
			throw new AuthorizationException("parameter principals is null");
		}
		//获取已认证用户的用户名
		String username = (String) super.getAvailablePrincipal(principals);
		
		//用户名获取角色名集合
		Set<String> roleNameSet = fourluckSecurity.getRoleNameSet(username);
		
		//权限集合
		Set<String> permissionNameSet = new HashSet<String>();
		
		if(roleNameSet != null && roleNameSet.size() > 0){
			for(String roleName : roleNameSet){
				permissionNameSet.addAll(fourluckSecurity.getPermissionNameSet(roleName));
			}
		}
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(roleNameSet);
		authorizationInfo.setStringPermissions(permissionNameSet);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if(token == null){
			throw new AuthenticationException("parameter token is null");
		}
		//表单提交过来的用户名
		String username = ((UsernamePasswordToken)token).getUsername();
		//获取用户数据库中记录的密码
		String password = fourluckSecurity.getPassword(username);
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
		authenticationInfo.setPrincipals(new SimplePrincipalCollection(username,super.getName()));
		authenticationInfo.setCredentials(password);
		return authenticationInfo;
	}

}
