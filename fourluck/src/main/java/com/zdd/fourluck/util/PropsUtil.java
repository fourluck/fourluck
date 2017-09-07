package com.zdd.fourluck.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属性文件工具类
 * @author four-luck
 *
 */
public class PropsUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);
	
	/**
	 * 加载属性文件
	 * @param fileName
	 * @return
	 */
	public static Properties loadProps(String fileName){
		Properties props = null;
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if(is == null){
				throw new Exception(fileName+" file is not found");
			}
			props = new Properties();
			props.load(is);
		} catch (Exception e) {
			LOGGER.error("load properties file fail",e);
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (Exception e2) {
					LOGGER.error("close input stream fail",e2);
				}
			}
		}
		return props;
	}
	
	/**
	 * 获取字符型属性(默认值为空字符串)
	 * @param properties
	 * @param key
	 * @return
	 */
	public static String getString(Properties properties,String key){
		return getString(properties,key,"");
	}

	/**
	 * 获取字符型属性 (可指定默认值)
	 * @param properties
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	private static String getString(Properties properties, String key, String defaultValue) {
		String value = defaultValue;
		if(properties.containsKey(key)){
			value = properties.getProperty(key);
		}
		return value;
	}
	
	/**
	 * 获取数值型属性(默认值为0)
	 * @param properties
	 * @param key
	 * @return
	 */
	public static int getInt(Properties properties,String key){
		return getInt(properties,key,0);
	}
	
	/**
	 * 获取数值型属性 (可指定默认值)
	 * @param properties
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	private static int getInt(Properties properties, String key, int defaultValue) {
		int value = defaultValue;
		if(properties.containsKey(key)){
			value = CastUtil.castInt(properties.getProperty(key));
		}
		return value;
	}
	
	/**
	 * 获取布尔型属性(默认值为false)
	 * @param properties
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(Properties properties,String key){
		return getBoolean(properties,key,false);
	}
	
	/**
	 * 获取布尔型属性 (可指定默认值)
	 * @param properties
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	private static boolean getBoolean(Properties properties, String key, boolean defaultValue) {
		boolean value = defaultValue;
		if(properties.containsKey(key)){
			value = CastUtil.castBoolean(properties.getProperty(key));
		}
		return value;
	}
}
