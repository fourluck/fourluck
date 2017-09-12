package com.zdd.framework.bean;

import java.lang.reflect.Method;

/**
 * 封装 Action 信息
 * @author four-luck
 * @since 1.0.0
 */
public class Handler {
	/**
	 * controller 类
	 */
	private Class<?> controllerClass;
	
	/**
	 * action 方法
	 */
	private Method actionMethod;
	
	public Handler(Class<?> controllerClass,Method actionMethod) {
		this.controllerClass = controllerClass;
		this.actionMethod = actionMethod;
	}

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public Method getActionMethod() {
		return actionMethod;
	}
}
