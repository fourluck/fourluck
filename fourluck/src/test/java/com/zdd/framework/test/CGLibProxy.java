package com.zdd.framework.test;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibProxy implements MethodInterceptor {
	
	private static CGLibProxy instance = new CGLibProxy();
	
	private CGLibProxy(){
		
	}
	
	public static CGLibProxy getInstance(){
		return instance;
	}
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> cls){
		return (T) Enhancer.create(cls, this);
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		before();
		Object resut = proxy.invokeSuper(obj, args);
		after();
		return resut;
	}

	private void before() {
		System.out.println("before method!!!");
	}

	private void after() {
		System.out.println("after method!!!");
	}
}
