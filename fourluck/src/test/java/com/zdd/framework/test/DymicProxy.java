package com.zdd.framework.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DymicProxy implements InvocationHandler {
	private Object target;
	
	public DymicProxy(Object target) {
		this.target = target;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(){
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object result = method.invoke(target, args);
		after();
		return result;
	}
	private void before() {
		System.out.println("before method!!!");
	}

	private void after() {
		System.out.println("after method!!!");
	}
}
