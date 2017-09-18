package com.zdd.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符工具类
 * @author four-luck
 *
 */
public class StringUtil {
	
	public static final String SEPARATOR = String.valueOf((char) 29);
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		return StringUtils.isBlank(str);
	}
	
	/**
	 * 判断字符串是否非空
	 * @param str
	 * @return
	 */
	public static boolean isNoEmpty(String str){
		return StringUtils.isNotBlank(str);
	}
	
}
