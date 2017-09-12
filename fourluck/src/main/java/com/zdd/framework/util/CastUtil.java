package com.zdd.framework.util;

/**
 * 转型操作工具类
 * @author four-luck
 *
 */
public class CastUtil {
	/**
	 * 转为String型 (默认为"")
	 * @param obj
	 * @return
	 */
	public static String castString(Object obj){
		return CastUtil.castString(obj,"");
	}

	/**
	 * 转为String型 (可指定默认值)
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static String castString(Object obj, String defaultValue) {
		return obj == null ? defaultValue : String.valueOf(obj);
	}
	
	/**
	 * 转为double型 (默认为0)
	 * @param obj
	 * @return
	 */
	public static double castDouble(Object obj){
		return CastUtil.castDouble(obj,0);
	}

	/**
	 * 转为double型 (可指定默认值)
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static double castDouble(Object obj, double defaultValue) {
		double dvalue = defaultValue;
		if(obj != null){
			String strValue = castString(obj);
			if(StringUtil.isNoEmpty(strValue)){
				try {
					dvalue = Double.parseDouble(strValue);
				} catch (NumberFormatException e) {
					dvalue = defaultValue;
				}
			}
		}
		return dvalue;
	}
	
	/**
	 * 转为long型 (默认为0)
	 * @param obj
	 * @return
	 */
	public static long castLong(Object obj){
		return CastUtil.castLong(obj,0);
	}
	
	/**
	 * 转为long型 (可指定默认值)
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static long castLong(Object obj, long defaultValue) {
		long lvalue = defaultValue;
		if(obj != null){
			String strValue = castString(obj);
			if(StringUtil.isNoEmpty(strValue)){
				try {
					lvalue = Long.parseLong(strValue);
				} catch (NumberFormatException e) {
					lvalue = defaultValue;
				}
			}
		}
		return lvalue;
	}
	
	
	/**
	 * 转为int型 (默认为0)
	 * @param obj
	 * @return
	 */
	public static int castInt(Object obj){
		return CastUtil.castInt(obj,0);
	}
	
	/**
	 * 转为int型 (可指定默认值)
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static int castInt(Object obj, int defaultValue) {
		int lvalue = defaultValue;
		if(obj != null){
			String strValue = castString(obj);
			if(StringUtil.isNoEmpty(strValue)){
				try {
					lvalue = Integer.parseInt(strValue);
				} catch (NumberFormatException e) {
					lvalue = defaultValue;
				}
			}
		}
		return lvalue;
	}
	
	/**
	 * 转为boolean型 (默认为false)
	 * @param obj
	 * @return
	 */
	public static boolean castBoolean(Object obj){
		return CastUtil.castBoolean(obj,false);
	}
	
	/**
	 * 转为boolean型 (可指定默认值)
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static boolean castBoolean(Object obj, boolean defaultValue) {
		boolean lvalue = defaultValue;
		if(obj != null){
			String strValue = castString(obj);
			if(StringUtil.isNoEmpty(strValue)){
				try {
					lvalue = Boolean.parseBoolean(strValue);
				} catch (NumberFormatException e) {
					lvalue = defaultValue;
				}
			}
		}
		return lvalue;
	}

}