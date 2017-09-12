package com.zdd.framework.proxy;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 代理管理器
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月11日 下午4:16:08
 */
public class ProxyManager {
	@SuppressWarnings("unchecked")
	public static <T> T creatProxy(final Class<?> targetClass,final List<Proxy> proxyList){
		return (T) Enhancer.create(targetClass, new MethodInterceptor(){
			@Override
			public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
				return new ProxyChain(targetClass, targetObject, targetMethod, methodProxy, methodParams, proxyList).doProxyChain();
			}
			
		});
	}
}
