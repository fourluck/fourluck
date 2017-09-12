package com.zdd.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.zdd.framework.util.ReflectionUtil;

/**
 * bean 助手类（工厂）
 * @author four-luck
 *
 */
public class BeanHelper {
	private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>, Object>();
	
	static{
		Set<Class<?>> classSet = ClassHelper.getBeanClassSet();
		for(Class<?> cls : classSet){
			Object obj = ReflectionUtil.newInstance(cls);
			BEAN_MAP.put(cls, obj);
		}
	}
	
	/**
	 * 获取bean映射
	 * @return
	 */
	public static Map<Class<?>,Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	/**
	 * 获取bean实例
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls){
		if(!BEAN_MAP.containsKey(cls)){
			throw new RuntimeException("can not get bean by class " + cls);
		}
		return (T) BEAN_MAP.get(cls);
	}
	
	/**
	 * 设置bean实例
	 * @param cls
	 * @param obj
	 */
	public static void setBean(Class<?> cls,Object obj){
		BEAN_MAP.put(cls, obj);
	}
}
