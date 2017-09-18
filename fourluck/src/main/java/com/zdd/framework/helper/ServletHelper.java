package com.zdd.framework.helper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServletHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServletHelper.class);
	
	private static final ThreadLocal<ServletHelper> SERVLET_HELPER_HOLDER = new ThreadLocal<ServletHelper>();
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;

	public ServletHelper(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	/**
	 * 初始化
	 * @param request
	 * @param response
	 */
	public static void init(HttpServletRequest request,HttpServletResponse response) {
		SERVLET_HELPER_HOLDER.set(new ServletHelper(request,response));
	}
	
	/**
	 * 销毁
	 */
	public static void destory() {
		SERVLET_HELPER_HOLDER.remove();
	}
	
	/**
	 * 获取 Request 对象
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		return	SERVLET_HELPER_HOLDER.get().request;
	}
	
	/**
	 * 获取 Response 对象
	 */
	public static HttpServletResponse getResponse(){
		return	SERVLET_HELPER_HOLDER.get().response;
	}
	
	/**
	 * 获取 Session 对象
	 */
	public static HttpSession getSession(){
		return getRequest().getSession();
	}
	
	/**
	 * 获取 ServletContext 对象
	 */
	public static ServletContext getServletContext(){
		return getRequest().getServletContext();
	}
	
	/**
	 * 将属性放入 Request 中
	 * @param key
	 * @param value
	 */
	public static void setRequestAttribute(String key,String value){
		getRequest().setAttribute(key, value);
	}
	
	/**
	 * 从 Request 中获取属性
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getRequestAttribute(String key){
		return (T) getRequest().getAttribute(key);
	}
	
	/**
	 * 从 Request 中移除属性
	 * @param key
	 * @return
	 */
	public static void removeRequestAttribute(String key){
		getRequest().removeAttribute(key);
	}
	
	/**
	 * 发送重定向响应
	 * @param location
	 */
	public static void sendRedirect(String location){
		try {
			getResponse().sendRedirect(getRequest().getContextPath() + location);
		} catch (Exception e) {
			LOGGER.error("redirect failure",e);
		}
	}
	
	/**
	 * 将属性放入 Session 中
	 * @param key
	 * @param value
	 */
	public static void setSessionAttribute(String key,Object value){
		getSession().setAttribute(key, value);
	}
	
	/**
	 * 从 Session 中获取属性
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttribute(String key){
		return (T) getSession().getAttribute(key);
	}
	
	/**
	 * 从 Session 中移除属性
	 * @param key
	 * @return
	 */
	public static void removeSessionAttribute(String key){
		getSession().removeAttribute(key);
	}
	
	/**
	 * 使Session 失效
	 * @param key
	 * @return
	 */
	public static void invalidateSession(){
		getSession().invalidate();
	}
	
}
