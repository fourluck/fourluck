package com.zdd.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import com.zdd.framework.annotation.Action;
import com.zdd.framework.bean.Handler;
import com.zdd.framework.bean.Request;
import com.zdd.framework.util.CollectionUtil;

/**
 * 控制器助手类
 * @author four-luck
 * @since 1.0.0
 */
public final class ControllerHelper {
	/**
	 * 用于存放请求与处理器的关系
	 */
	private static final Map<Request,Handler> ACTION_MAP = new HashMap<Request, Handler>();
	
	static{
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		if(CollectionUtil.isNotEmpty(controllerClassSet)){
			for(Class<?> controllerClass : controllerClassSet){
				Method[] methods = controllerClass.getDeclaredMethods();
				if(ArrayUtils.isNotEmpty(methods)){
					for(Method method : methods){
						if(method.isAnnotationPresent(Action.class)){
							//从 Action 注解中获取URL映射规则
							Action action = method.getAnnotation(Action.class);
							String mapping = action.value();
							if(mapping.matches("\\w+:/\\w*")){
								String[] array = mapping.split(":");
								if(ArrayUtils.isNotEmpty(array)&&array.length == 2){
									//获取请求方法和请求路径
									String requestMethod = array[0];
									String requestPath = array[1];
									Request request = new Request(requestMethod, requestPath);
									Handler handler = new Handler(controllerClass, method);
									ACTION_MAP.put(request, handler);
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 获取 Handler
	 * @param requestMethod
	 * @param requestPath
	 * @return
	 */
	public static Handler getHandler(String requestMethod,String requestPath){
		Request request = new Request(requestMethod, requestPath);
		return ACTION_MAP.get(request);
	}
}
