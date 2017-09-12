package com.zdd.framework.bean;
/**
 * 返回视图对象
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月8日 下午1:33:24
 */

import java.util.HashMap;
import java.util.Map;

public class View {
	/**
	 * 视图路径
	 */
	private String path;
	
	/**
	 * 模型数据
	 */
	private Map<String, Object> model;
	
	public View(String path) {
		this.path = path;
		model = new HashMap<String, Object>();
	}
	
	public View addModel(String key,Object value){
		model.put(key, value);
		return this;
	}
	
	public String getPath(){
		return path;
	}

	public Map<String, Object> getModel() {
		return model;
	}

}
