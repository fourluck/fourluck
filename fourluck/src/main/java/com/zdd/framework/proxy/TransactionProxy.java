package com.zdd.framework.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zdd.framework.annotation.Transaction;
import com.zdd.framework.helper.DatabaseHelper;

/**
 * 事务代理
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月12日 下午2:23:11
 */
public class TransactionProxy implements Proxy {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);
	
	private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>(){
		@Override
		protected Boolean initialValue() {
			return false;
		};
	};
	
	@Override
	public Object doProxy(ProxyChain proxyChain) throws Throwable {
		Object result;
		boolean flag = FLAG_HOLDER.get();
		Method method = proxyChain.getTargetMethod();
		if(!flag && method.isAnnotationPresent(Transaction.class)){
			FLAG_HOLDER.set(true);
			try {
				DatabaseHelper.beginTransaction();
				LOGGER.debug("begin transaction");
				result = proxyChain.doProxyChain();
				DatabaseHelper.commitTransaction();
				LOGGER.debug("commit transaction");
			} catch (Exception e) {
				DatabaseHelper.rollbackTransaction();
				LOGGER.debug("rollback transaction");
				throw e;
			} finally {
				FLAG_HOLDER.remove();
			}
		}else{
			result = proxyChain.doProxyChain();
		}
		return result;
	}

}
