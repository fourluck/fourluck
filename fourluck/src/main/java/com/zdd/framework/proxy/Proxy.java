package com.zdd.framework.proxy;

/**
 * 代理接口
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月11日 下午3:41:05
 */
public interface Proxy {
	/**
	 * 执行链式代理
	 * @param proxyChain
	 * @return
	 * @throws Throwable
	 */
	Object doProxy(ProxyChain proxyChain)throws Throwable;
}
