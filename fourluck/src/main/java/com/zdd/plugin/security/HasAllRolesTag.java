package com.zdd.plugin.security;

import java.util.Arrays;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.RoleTag;

public class HasAllRolesTag extends RoleTag {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1432326176603910834L;
	
	private static final String ROLE_NAMES_DELIMITER = ",";

	@Override
	protected boolean showTagBody(String roleName) {
		boolean hasAllRoles = false;
		Subject subject = getSubject();
		if(subject != null){
			if(subject.hasAllRoles(Arrays.asList(roleName.split(ROLE_NAMES_DELIMITER)))){
				hasAllRoles = true;
			}
		}
		return hasAllRoles;
	}

}
