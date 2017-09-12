package com.zdd.framework.helper;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import com.zdd.framework.annotation.Controller;
import com.zdd.framework.annotation.Service;
import com.zdd.framework.util.ClassUtil;

/**
 * 类操作助手
 * @author four-luck
 * @since 1.0.0
 *
 */
public final class ClassHelper {
	/**
	 * 集合类（用于存放所加载的类）
	 */
	private static final Set<Class<?>> CLASS_SET;
	static{
		CLASS_SET = ClassUtil.getClassSet(ConfigHelper.getAppBasePackage());
	}
	
	/**
	 * 获取应用包名下所有类
	 * @return
	 */
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	
	/**
	 * 获取应用包名下所有service类
	 * @return
	 */
	public static Set<Class<?>> getServiceClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls : CLASS_SET){
			if(cls.isAnnotationPresent(Service.class)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * 获取应用包名下所有controller类
	 * @return
	 */
	public static Set<Class<?>> getControllerClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls : CLASS_SET){
			if(cls.isAnnotationPresent(Controller.class)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * 获取应用包名下所有bean类
	 * @return
	 */
	public static Set<Class<?>> getBeanClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		classSet.addAll(getServiceClassSet());
		classSet.addAll(getControllerClassSet());
		return classSet;
	}
	
	/**
	 * 获取应用包名下某父类（或接口）的所有子类（或实现类）
	 * @param superClass
	 * @return
	 */
	public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls : CLASS_SET){
			if(superClass.isAssignableFrom(cls)&&!superClass.equals(cls)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * 获取应用包名下带有某注解的所有类
	 * @param superClass
	 * @return
	 */
	public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls : CLASS_SET){
			if(cls.isAnnotationPresent(annotationClass)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	
}
