package com.zdd.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON 工具类
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月8日 下午3:05:53
 */
public final class JsonUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	/**
	 * 将POJO转为JSON
	 * @param pojo
	 * @return
	 */
	public static <T> String toJson(T pojo){
		String json;
		try {
			json = OBJECT_MAPPER.writeValueAsString(pojo);
		} catch (Exception e) {
			LOGGER.error("convert POJO to JSON failure",e);
			throw new RuntimeException(e);
		}
		
		return json;
	}
	
	/**
	 * 将JSON转为POJO
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String json,Class<T> type){
		T pojo;
		try {
			pojo = OBJECT_MAPPER.readValue(json, type);
		} catch (Exception e) {
			LOGGER.error("convert JSON to POJO failure",e);
			throw new RuntimeException(e);
		}
		return pojo;
	}
}
