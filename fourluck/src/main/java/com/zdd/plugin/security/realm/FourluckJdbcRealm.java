package com.zdd.plugin.security.realm;

import org.apache.shiro.realm.jdbc.JdbcRealm;

import com.zdd.framework.helper.DatabaseHelper;
import com.zdd.plugin.security.SecurityConfig;
import com.zdd.plugin.security.password.Md5CredentialsMatcher;

public class FourluckJdbcRealm extends JdbcRealm {
	public FourluckJdbcRealm() {
		super.setDataSource(DatabaseHelper.getDataSource());
		super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());
		super.setUserRolesQuery(SecurityConfig.getJdbcRolesQuery());
		super.setPermissionsQuery(SecurityConfig.getJdbcPermissionsQuery());
		super.setPermissionsLookupEnabled(true);
		super.setCredentialsMatcher(new Md5CredentialsMatcher());
	}
}
