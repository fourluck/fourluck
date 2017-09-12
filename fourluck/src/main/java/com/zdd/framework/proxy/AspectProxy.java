package com.zdd.framework.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 切面代理
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月11日 下午4:40:59
 */
public abstract class AspectProxy implements Proxy {
	private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);
	
	@Override
	public Object doProxy(ProxyChain proxyChain) throws Throwable {
		Object result = null;
		Class<?> cls = proxyChain.getClass();
		Method method = proxyChain.getTargetMethod();
		Object[] params = proxyChain.getMethodParams();
		
		begin();
		try {
			if(intercept(cls,method,params)){
				before(cls,method,params);
				result = proxyChain.doProxyChain();
				after(cls, method, params,result);
			}else{
				result = proxyChain.doProxyChain();
			}
		} catch (Exception e) {
			LOGGER.error("proxy failure",e);
			error(cls, method, params,e);
			throw e;
		} finally {
			end();
		}
		return result;
	}
	
	public void begin() {
		// TODO Auto-generated method stub
		
	}

	public boolean intercept(Class<?> cls, Method method, Object[] result)throws Throwable {
		// TODO Auto-generated method stub
		return true;
	}

	public void before(Class<?> cls, Method method, Object[] params)throws Throwable {
		// TODO Auto-generated method stub
		
	}
	
	public void after(Class<?> cls, Method method, Object[] params, Object result)throws Throwable {
		// TODO Auto-generated method stub
		
	}
	
	public void error(Class<?> cls, Method method, Object[] params, Throwable e) {
		// TODO Auto-generated method stub
		
	}
	
	public void end() {
		// TODO Auto-generated method stub
		
	}
}
