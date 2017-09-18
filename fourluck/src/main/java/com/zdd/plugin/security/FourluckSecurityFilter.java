package com.zdd.plugin.security;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.CachingSecurityManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;

import com.zdd.framework.util.StringUtil;
import com.zdd.plugin.security.realm.FourluckCustomRealm;
import com.zdd.plugin.security.realm.FourluckJdbcRealm;

public class FourluckSecurityFilter extends ShiroFilter{
	@Override
	public void init() throws Exception {
		super.init();
		
		WebSecurityManager webSecurityManager = super.getSecurityManager();
		//设置Realm,可同时支持多个Realm,并按照先后顺序用逗号分割
		setRealms(webSecurityManager);
		//设置Cache
		setCache(webSecurityManager);
	}

	private void setRealms(WebSecurityManager webSecurityManager) {
		String securityRealms = SecurityConfig.getRealms();
		if(StringUtil.isNoEmpty(securityRealms)){
			String[] securityRealmsArray = securityRealms.split(",");
			if(securityRealmsArray.length > 0){
				Set<Realm> realms = new LinkedHashSet<Realm>();
				for(String securityRealm : securityRealmsArray){
					if(securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_JDBC)){
						//添加基于 JDBC 的Realm,需配置相关 SQL 查询语句
						addJdbcRealm(realms);
					}else if(securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_CUSTOM)){
						//添加基于定制化的Realm,需实现 FourluckSecurity 接口
						addCustomRealm(realms);
					}
				}
				RealmSecurityManager realmSecurityManager = (RealmSecurityManager) webSecurityManager;
				realmSecurityManager.setRealms(realms);
				
			}
		}
	}

	private void setCache(WebSecurityManager webSecurityManager) {
		if(SecurityConfig.isCacheable()){
			CachingSecurityManager cachingSecurityManager = (CachingSecurityManager) webSecurityManager;
			//基于内存的 CacheManager
			CacheManager cacheManager = new MemoryConstrainedCacheManager();
			cachingSecurityManager.setCacheManager(cacheManager);
		}
		
	}
	
	private void addJdbcRealm(Set<Realm> realms) {
		FourluckJdbcRealm fourluckJdbcRealm = new FourluckJdbcRealm();
		realms.add(fourluckJdbcRealm);
	}
	
	private void addCustomRealm(Set<Realm> realms) {
		FourluckSecurity fourluckSecurity = SecurityConfig.getFourluckSecurity();
		//添加自己实现的 realm
		FourluckCustomRealm fourluckCustomRealm = new FourluckCustomRealm(fourluckSecurity);
		realms.add(fourluckCustomRealm);
	}
}
