package com.zdd.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import com.zdd.framework.annotation.Inject;
import com.zdd.framework.util.CollectionUtil;
import com.zdd.framework.util.ReflectionUtil;

/**
 * 依赖注入助手类
 * @author four-luck
 * @since 1.0.0
 */
public final class IocHelper {
	static {
		Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
		if(CollectionUtil.isNotEmpty(beanMap)){
			for(Map.Entry<Class<?>,Object> beanEntry : beanMap.entrySet()){
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				//获取bean类定义的所有成员变量
				Field[] beanFields = beanClass.getDeclaredFields();
				if(ArrayUtils.isNotEmpty(beanFields)){
					for(Field beanField : beanFields){
						//如果成员变量带有Inject注解
						if(beanField.isAnnotationPresent(Inject.class)){
							//获取成员变量的实例
							Class<?> beanFieldClass = beanField.getType();
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							if(beanFieldInstance != null){
								//通过反射初始化 成员变量的值
								ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
							}
						}
					}
				}
			}
		}
	}
}
