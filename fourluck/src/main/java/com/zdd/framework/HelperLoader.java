package com.zdd.framework;

import com.zdd.framework.helper.AopHelper;
import com.zdd.framework.helper.BeanHelper;
import com.zdd.framework.helper.ClassHelper;
import com.zdd.framework.helper.ControllerHelper;
import com.zdd.framework.helper.IocHelper;
import com.zdd.framework.util.ClassUtil;

/**
 * 加载相应的 helper类
 * @author four-luck
 * @since 1.0.0
 */
public final class HelperLoader {
	public static void init() {
		Class<?>[] classList = {
			ClassHelper.class,
			BeanHelper.class,
			AopHelper.class,
			IocHelper.class,
			ControllerHelper.class
		};
		
		for(Class<?> cls : classList){
			ClassUtil.loadClass(cls.getName());
		}
	}
	
	public static void main(String[] args) {
		new AopHelper();
	}
}
