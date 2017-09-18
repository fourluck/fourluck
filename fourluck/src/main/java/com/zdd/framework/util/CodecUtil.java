package com.zdd.framework.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于编码和解码操作
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月8日 下午2:58:37
 */
public final class CodecUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);
	
	/**
	 * 编码 url
	 * @param source
	 * @return
	 */
	public static String encodeURL(String source){
		String target;
		try {
			target = URLEncoder.encode(source,"UTF-8");
		} catch (Exception e) {
			LOGGER.error("encode url failure",e);
			throw new RuntimeException(e);
		}
		return target;
	}
	
	/**
	 * url 解码
	 * @param source
	 * @return
	 */
	public static String decodeURL(String source){
		String target;
		try {
			target = URLDecoder.decode(source,"UTF-8");
		} catch (Exception e) {
			LOGGER.error("decode url failure",e);
			throw new RuntimeException(e);
		}
		return target;
	}
	
	public static String md5(String source){
		return DigestUtils.md5Hex(source);
	}
}
