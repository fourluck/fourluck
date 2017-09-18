package com.zdd.plugin.security;

/**
 * 权限认证常量
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月14日 下午4:25:28
 */
public interface SecurityConstant {
	String REALMS = "fourluck.plugin.security.realms";
	String REALMS_JDBC = "jdbc";
	String REALMS_CUSTOM = "custom";
	
	String FOURLUCK_SECURITY = "fourluck.plugin.security.custom.class";
	
	String JDBC_AUTHC_QUERY = "fourluck.plugin.security.jdbc_authc_query";
	String JDBC_ROLES_QUERY = "fourluck.plugin.security.jdbc_roles_query";
	String JDBC_PERMISSIONS_QUERY = "fourluck.plugin.security.jdbc_permissions_query";
	
	String CACHE = "fourluck.plugin.security.cache";
}
