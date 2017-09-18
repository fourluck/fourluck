package com.zdd.framework;

import java.util.Set;

import com.zdd.framework.helper.DatabaseHelper;
import com.zdd.plugin.security.FourluckSecurity;

/**
 * 应用安全控制
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月14日 上午10:27:20
 */
public class AppSecurity implements FourluckSecurity {

	@Override
	public String getPassword(String username) {
		String sql = "select password from user where username = ?";
		return DatabaseHelper.query(sql, username);
	}

	@Override
	public Set<String> getRoleNameSet(String username) {
		String sql = "select r.role_name from user u,user_role ur,role r"
				+ " where u.id=ur.user_id and r.id=ur_role_id and u.username = ?";
		return DatabaseHelper.querySet(sql, username);
	}

	@Override
	public Set<String> getPermissionNameSet(String roleName) {
		String sql = "select p.permission_name from role r,role_permission rp,permission p where r.id=rp.role_id "
				+ "and p.id=rp.permission_id and r.role_name = ?";
		return DatabaseHelper.querySet(sql, roleName);
	}

}
