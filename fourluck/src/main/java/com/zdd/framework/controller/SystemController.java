package com.zdd.framework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zdd.framework.annotation.Action;
import com.zdd.framework.annotation.Controller;
import com.zdd.framework.bean.Param;
import com.zdd.framework.bean.View;
import com.zdd.plugin.security.SecurityHelper;
import com.zdd.plugin.security.exception.AuthcException;
@Controller
public class SystemController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SystemController.class);
	
	/**
	 * 进入首页
	 */
	@Action("get:/")
	public View index(){
		return new View("index.jsp");
	}
	
	/**
	 * 进入登录页面
	 */
	@Action("get:/login")
	public View login(){
		return new View("login.jsp");
	}
	
	/**
	 * 登录操作
	 */
	@Action("post:/login")
	public View loginDo(Param param){
		String username = param.getString("username");
		String password = param.getString("password");
		try {
			SecurityHelper.login(username, password);
		} catch (AuthcException e) {
			LOGGER.error("login failure",e);
			return new View("/login");
		}
		return new View("/customer");
	}
	
	
	/**
	 * 注销
	 */
	@Action("get:/logout")
	public View logout(){
		SecurityHelper.logout();
		return new View("/");
	}
}
