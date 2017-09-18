package com.zdd.plugin.security;

import com.zdd.framework.helper.ConfigHelper;
import com.zdd.framework.util.ReflectionUtil;

/**
 * 从权限认证配置文件中获取相关属性
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月14日 下午5:11:27
 */
public final class SecurityConfig {
	
	public static String getRealms() {
		return ConfigHelper.getString(SecurityConstant.REALMS);
	}
	
	public static FourluckSecurity getFourluckSecurity(){
		String className = ConfigHelper.getString(SecurityConstant.FOURLUCK_SECURITY);
		return (FourluckSecurity) ReflectionUtil.newInstance(className);
	}
	
	public static String getJdbcAuthcQuery() {
		return ConfigHelper.getString(SecurityConstant.JDBC_AUTHC_QUERY);
	}
	
	public static String getJdbcRolesQuery() {
		return ConfigHelper.getString(SecurityConstant.JDBC_ROLES_QUERY);
	}
	
	public static String getJdbcPermissionsQuery() {
		return ConfigHelper.getString(SecurityConstant.JDBC_PERMISSIONS_QUERY);
	}
	
	public static boolean isCacheable(){
		return ConfigHelper.getBoolean(SecurityConstant.JDBC_PERMISSIONS_QUERY);
	}
}
