package com.zdd.framework.bean;

import java.util.Map;

import com.zdd.framework.util.CastUtil;

/**
 * 请求参数对象
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月8日 下午1:24:59
 */
public class Param {
	private Map<String,Object> paramMap;
	
	public Param(Map<String,Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	public long getLong(String name){
		return CastUtil.castLong(paramMap.get(name));
	}
	
	public Map<String,Object> getMap(){
		return paramMap;
	}
}
