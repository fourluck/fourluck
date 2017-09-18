package com.zdd.plugin.security;

import java.util.Set;

/**
 * Security 接口
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月13日 下午5:30:05
 */
public interface FourluckSecurity {
	/**
	 * 根据用户名获取密码
	 * @param username
	 * @return 密码
	 */
	String getPassword(String username);
	
	/**
	 * 根据用户名获取角色名集合
	 * @param username
	 * @return 角色名集合
	 */
	Set<String> getRoleNameSet(String username);
	
	/**
	 * 根据角色名获取权限名集合
	 * @param roleName
	 * @return 权限名集合
	 */
	Set<String> getPermissionNameSet(String roleName);
}
