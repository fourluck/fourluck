package com.zdd.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符工具类
 * @author four-luck
 *
 */
public class StringUtil {
	
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
