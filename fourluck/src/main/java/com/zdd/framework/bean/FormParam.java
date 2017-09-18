package com.zdd.framework.bean;

/**
 * 表单参数封装类
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月12日 下午4:16:23
 */
public class FormParam {
	private String fieldName;
	
	private Object fieldValue;
	
	public FormParam(String fieldName, Object fieldValue) {
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public Object getFieldValue() {
		return fieldValue;
	}
	
}
