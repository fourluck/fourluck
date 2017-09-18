package com.zdd.plugin.security;

import java.util.Set;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.shiro.web.env.EnvironmentLoaderListener;

/**
 * fourluck Security插件
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月14日 下午3:37:39
 */
public class FourluckSecurityPlugin implements ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		ctx.setInitParameter("shiroConfigLocation", "classpath:shiro.ini");
		ctx.addListener(EnvironmentLoaderListener.class);
		FilterRegistration.Dynamic fourluckSecurityFilter = ctx.addFilter("FourluckSecurityFilter", FourluckSecurityFilter.class);
		fourluckSecurityFilter.addMappingForUrlPatterns(null, false, "/*");
	}

}
