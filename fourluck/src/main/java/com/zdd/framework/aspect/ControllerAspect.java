package com.zdd.framework.aspect;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zdd.framework.annotation.Aspect;
import com.zdd.framework.annotation.Controller;
import com.zdd.framework.proxy.AspectProxy;

/**
 * 拦截 Controller 所有方法
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月11日 下午5:18:01
 */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
	
	private long begin;
	
	@Override
	public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
		LOGGER.debug("------begin------");
		LOGGER.debug(String.format("class:%s",cls.getName()));
		LOGGER.debug(String.format("method:%s",method.getName()));
		begin = System.currentTimeMillis();
	}
	
	@Override
	public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
		LOGGER.debug(String.format("time:%dms",System.currentTimeMillis() - begin));
		LOGGER.debug("------end------");
	}
}
