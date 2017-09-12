package com.zdd.framework.bean;

/**
 * 返回 数据 对象
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月8日 下午1:51:49
 */
public class Data {
	/**
	 * 模型数据
	 */
	private Object model;
	
	public Data(Object model) {
		this.model = model;
	}
	
	public Object getModel() {
		return model;
	}
}
